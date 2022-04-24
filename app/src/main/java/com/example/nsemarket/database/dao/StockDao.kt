package com.example.nsemarket.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nsemarket.database.tables.Stock
import com.example.nsemarket.database.tables.StockStockTypeCrossRef

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stock: Stock)

    @Update
    fun update(stock: Stock)

    @Delete
    fun delete(stock: Stock)

    @Query("DELETE FROM stock_table")
    fun deleteAllStocks()

    @Query("SELECT * FROM stock_table ORDER BY change DESC")
    fun allStocks(): LiveData<List<Stock>>

    @Query("SELECT * FROM stock_table s inner join StockStockTypeCrossRef st on s.identifier=st.identifier where st.stockType = 'NIFTY50' and s.identifier != 'NIFTY 50'")
    fun allNifty50Stocks(): LiveData<List<Stock>>

    @Query("SELECT count(*) FROM stock_table s inner join StockStockTypeCrossRef st on s.identifier=st.identifier where st.stockType = 'NIFTY50' and s.identifier != 'NIFTY 50'")
    fun countOfNifty50(): LiveData<Int>

    @Query("SELECT * FROM stock_table s inner join StockStockTypeCrossRef st on s.identifier=st.identifier where st.stockType = 'NIFTYBANK' and s.identifier != 'NIFTY BANK'")
    fun allNiftyBankStocks(): LiveData<List<Stock>>

    @Query("SELECT * FROM stock_table s inner join StockStockTypeCrossRef st on s.identifier=st.identifier where st.stockType = 'NIFTYIT' and s.identifier != 'NIFTY IT'")
    fun allNiftyITStocks(): LiveData<List<Stock>>

    @Query("SELECT count(*) FROM stock_table s inner join StockStockTypeCrossRef st on s.identifier=st.identifier where st.stockType = 'NIFTYBANK' and s.identifier != 'NIFTY BANK'")
    fun countOfNiftyBank(): LiveData<Int>

    @Query("SELECT * FROM stock_table where identifier='NIFTY 50'")
    fun nifty50(): LiveData<Stock>

    @Query("SELECT * FROM stock_table where identifier='NIFTY BANK'")
    fun niftyBank(): LiveData<Stock>

    @Query("SELECT * FROM stock_table where identifier='NIFTY IT'")
    fun niftyIT(): LiveData<Stock>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stockStockTypeCrossRef: StockStockTypeCrossRef)
}