package com.example.nsemarket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nsemarket.database.dao.StockDao
import com.example.nsemarket.database.dao.StockTypeDao
import com.example.nsemarket.database.tables.Stock
import com.example.nsemarket.database.tables.StockStockTypeCrossRef
import com.example.nsemarket.database.tables.StockType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Stock::class, StockType::class, StockStockTypeCrossRef::class], version = 2)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
    abstract fun stockTypeDao(): StockTypeDao

    companion object {
        private const val TAG = "StockDatabase"
        @Volatile
        private var INSTANCE: StockDatabase? = null


        fun getDatabase(context: Context, scope: CoroutineScope): StockDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockDatabase::class.java,
                        "stock_database"
                ).addCallback(StockDatabaseCallBack(scope)).build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabase(context: Context): StockDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockDatabase::class.java,
                        "stock_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class StockDatabaseCallBack(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //  can populate database here
                }
            }
        }
    }
}