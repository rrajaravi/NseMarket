package com.example.nsemarket.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.nsemarket.database.tables.StockType

@Dao
interface StockTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stockType: StockType)
}