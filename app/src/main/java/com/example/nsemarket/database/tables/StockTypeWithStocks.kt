package com.example.nsemarket.database.tables

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class StockTypeWithStocks {
    @Embedded
    var stockType: StockType? = null

    @Relation(parentColumn = "stockType", entityColumn = "identifier", associateBy = Junction(StockStockTypeCrossRef::class))
    var stocks: List<Stock>? = null
}