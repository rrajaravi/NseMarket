package com.example.nsemarket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Metadata {
    @SerializedName("indexName")
    @Expose
    var indexName: String? = null

    @SerializedName("open")
    @Expose
    var open: Double? = null

    @SerializedName("high")
    @Expose
    var high: Double? = null

    @SerializedName("low")
    @Expose
    var low: Double? = null

    @SerializedName("previousClose")
    @Expose
    var previousClose: Double? = null

    @SerializedName("last")
    @Expose
    var last: Double? = null

    @SerializedName("percChange")
    @Expose
    var percChange: Double? = null

    @SerializedName("change")
    @Expose
    var change: Double? = null

    @SerializedName("timeVal")
    @Expose
    var timeVal: String? = null

    @SerializedName("yearHigh")
    @Expose
    var yearHigh: Double? = null

    @SerializedName("yearLow")
    @Expose
    var yearLow: Double? = null

    @SerializedName("totalTradedVolume")
    @Expose
    var totalTradedVolume: Int? = null

    @SerializedName("totalTradedValue")
    @Expose
    var totalTradedValue: Double? = null

    @SerializedName("ffmc_sum")
    @Expose
    var ffmcSum: Double? = null

}