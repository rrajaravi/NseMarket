package com.example.nsemarket

import android.app.IntentService
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.example.nsemarket.data.source.StockRepository
import com.example.nsemarket.database.StockDatabase
import com.example.nsemarket.database.dao.StockDao
import com.example.nsemarket.database.tables.Stock
import com.example.nsemarket.database.tables.StockStockTypeCrossRef
import com.example.nsemarket.models.NseResponse
import com.example.nsemarket.rest.NSEApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NSEPullService : IntentService("NSEPullService") {

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: ")
        val database = StockDatabase.getDatabase(this)
        val stockDao = database.stockDao()
        val stockType = intent?.getStringExtra(Constants.STOCK_TYPE_INTENT)
        Log.d(TAG, "onHandleIntent: stockType: $stockType")
        if (stockType != null) {
            getAllRemoteStocks(stockType, stockDao)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


    private class PopulateDbAsyncTask(private val stockDao: StockDao?, private val allStocks: List<Stock>?, private val stockType: String) : AsyncTask<Void, Void, Void>() {
        protected override fun doInBackground(vararg voids: Void): Void? {
            if (allStocks != null) {
                for (stock in allStocks) {
                    stockDao!!.insert(stock)
                    val stockStockTypeCrossRef = StockStockTypeCrossRef(stockType, stock.identifier)
                    stockDao.insert(stockStockTypeCrossRef)
                }
            }
            return null
        }

    }

    fun getAllRemoteStocks(stockType: String, stockDao: StockDao) {
        var call = NSEApi.retrofitService.nifty50
        if (stockType == Constants.NIFTYBANK) {
            call = NSEApi.retrofitService.niftyBank
        } else if (stockType == Constants.NIFTYIT) {
            call = NSEApi.retrofitService.niftyIT
        }
        call!!.enqueue(object : Callback<NseResponse?> {
            override fun onResponse(call: Call<NseResponse?>, response: Response<NseResponse?>) {
                if (!response.isSuccessful) {
                    Log.e(TAG, "MESSAGE: $response")
                    Log.e(TAG, "Headers: " + response.headers())
                    Log.e(TAG, "onResponse: failure - " + response.code())
                    return
                }
                Log.d(TAG, "onResponse: Remote output came")
                val nseResponse = response.body()
                PopulateDbAsyncTask(stockDao, nseResponse?.data, stockType).execute()

//                for (stock in nseResponse?.data!!) {
//                    stockDao.insert(stock)
//                    val stockStockTypeCrossRef = StockStockTypeCrossRef(stockType, stock.identifier)
//                    stockDao.insert(stockStockTypeCrossRef)
//                }
                Log.d(TAG, "onResponse: Remote output came1")
            }

            override fun onFailure(call: Call<NseResponse?>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }
        })
    }

    companion object {
        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        private const val TAG = "NSEPullService"
    }

}