package com.example.nsemarket.models

import com.example.nsemarket.database.tables.Stock
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NseResponse {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("advance")
    @Expose
    var advance: Advance? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Stock>? = null

    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null

    @SerializedName("marketStatus")
    @Expose
    var marketStatus: MarketStatus? = null

    @SerializedName("date30dAgo")
    @Expose
    var date30dAgo: String? = null

    @SerializedName("date365dAgo")
    @Expose
    var date365dAgo: String? = null

}