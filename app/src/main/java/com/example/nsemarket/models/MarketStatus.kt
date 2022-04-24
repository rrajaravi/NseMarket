package com.example.nsemarket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MarketStatus {
    @SerializedName("market")
    @Expose
    var market: String? = null

    @SerializedName("marketStatus")
    @Expose
    var marketStatus: String? = null

    @SerializedName("tradeDate")
    @Expose
    var tradeDate: String? = null

    @SerializedName("index")
    @Expose
    var index: String? = null

    @SerializedName("last")
    @Expose
    var last: Double? = null

    @SerializedName("variation")
    @Expose
    var variation: Double? = null

    @SerializedName("percentChange")
    @Expose
    var percentChange: Double? = null

    @SerializedName("marketStatusMessage")
    @Expose
    var marketStatusMessage: String? = null

}