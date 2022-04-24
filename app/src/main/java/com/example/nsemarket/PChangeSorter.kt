package com.example.nsemarket

import com.example.nsemarket.database.tables.Stock
import java.util.*

class PChangeSorter : Comparator<Stock> {
    override fun compare(o1: Stock, o2: Stock): Int {
        return o2.pChange.compareTo(o1.pChange)
    }
}