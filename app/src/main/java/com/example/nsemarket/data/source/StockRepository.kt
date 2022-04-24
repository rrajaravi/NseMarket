package com.example.nsemarket.data.source

import androidx.lifecycle.LiveData
import com.example.nsemarket.Constants
import com.example.nsemarket.database.dao.StockDao
import com.example.nsemarket.database.StockDatabase
import com.example.nsemarket.database.tables.Stock

class StockRepository(private val stockDao: StockDao) {
    val allNiftyBankStocks: LiveData<List<Stock>>
    val allNifty50Stocks: LiveData<List<Stock>>
    val allNiftyITStocks: LiveData<List<Stock>>
    val nifty50: LiveData<Stock>
    val niftyBank: LiveData<Stock>
    val countOfNifty50: LiveData<Int>
    val countOfNiftyBank: LiveData<Int>
    private val niftyIT: LiveData<Stock>

    fun getIndexStock(stockType: String): LiveData<Stock> {
        return if (stockType == Constants.NIFTYIT) {
            niftyIT
        } else nifty50
    }

    companion object {
        private const val TAG = "StockRepository"
    }

    init {
        allNiftyBankStocks = stockDao.allNiftyBankStocks()
        allNifty50Stocks = stockDao.allNifty50Stocks()
        allNiftyITStocks = stockDao.allNiftyITStocks()
        nifty50 = stockDao.nifty50()
        niftyBank = stockDao.niftyBank()
        niftyIT = stockDao.niftyIT()
        countOfNifty50 = stockDao.countOfNifty50()
        countOfNiftyBank = stockDao.countOfNiftyBank()
    }
}