package com.example.nsemarket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nsemarket.Constants
import com.example.nsemarket.data.source.StockRepository
import com.example.nsemarket.database.StockDatabase
import com.example.nsemarket.database.dao.StockDao
import com.example.nsemarket.database.tables.Stock

class StockViewModel(application: Application) : AndroidViewModel(application) {
    val repository: StockRepository
    val stockDao: StockDao
    val allNifty50Stocks: LiveData<List<Stock>>
    val allNiftyITStocks: LiveData<List<Stock>>
    val allNiftyBankStocks: LiveData<List<Stock>>
    val nifty50: LiveData<Stock>
    val niftyBank: LiveData<Stock>
//    val niftyIT: LiveData<Stock>
    val countOfNifty50: LiveData<Int>
    val countOfNiftyBank: LiveData<Int>

    init {
        stockDao = StockDatabase.getDatabase(application, viewModelScope).stockDao()
        repository = StockRepository(stockDao)
        allNifty50Stocks = repository.allNifty50Stocks
        allNiftyITStocks = repository.allNiftyITStocks
        allNiftyBankStocks = repository.allNiftyBankStocks
        nifty50 = repository.nifty50
        niftyBank = repository.niftyBank
        countOfNifty50 = repository.countOfNifty50
        countOfNiftyBank = repository.countOfNiftyBank
    }

    fun getIndexStock(stockType: String): LiveData<Stock> {
        return repository.getIndexStock(stockType)
    }

}