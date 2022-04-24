package com.example.nsemarket.database.tables

import androidx.room.Entity

@Entity(primaryKeys = ["stockType", "identifier"])
class StockStockTypeCrossRef(var stockType: String, var identifier: String)