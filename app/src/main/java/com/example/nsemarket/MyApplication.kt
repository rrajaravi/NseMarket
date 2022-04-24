package com.example.nsemarket

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.util.Log
import com.example.nsemarket.data.source.StockRepository
import com.example.nsemarket.database.StockDatabase
import com.example.nsemarket.database.dao.StockDao

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")

        val intent = Intent(applicationContext, NSEPullService::class.java)
        intent.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYBANK)
        startService(intent)
        val intent1 = Intent(applicationContext, NSEPullService::class.java)
        intent1.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTY50)
        startService(intent1)
        val intent2 = Intent(applicationContext, NSEPullService::class.java)
        intent2.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYIT)
        startService(intent2)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, NSEPullService::class.java)
            intent.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYBANK)
            startService(intent)
            val intent1 = Intent(applicationContext, NSEPullService::class.java)
            intent1.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTY50)
            startService(intent1)
            val intent2 = Intent(applicationContext, NSEPullService::class.java)
            intent2.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYIT)
            startService(intent2)
        }, 120000)
    }

    companion object {
        private const val TAG = "MyApplication"
        private var stockDao: StockDao? = null
    }
}