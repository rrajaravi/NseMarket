package com.example.nsemarket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("priority")
    @Expose
    var priority: Int? = null

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

    @SerializedName("identifier")
    @Expose
    var identifier: String? = null

    @SerializedName("series")
    @Expose
    var series: String? = null

    @SerializedName("open")
    @Expose
    var open: Double? = null

    @SerializedName("dayHigh")
    @Expose
    var dayHigh: Double? = null

    @SerializedName("dayLow")
    @Expose
    var dayLow: Double? = null

    @SerializedName("lastPrice")
    @Expose
    var lastPrice: Double? = null

    @SerializedName("previousClose")
    @Expose
    var previousClose: Double? = null

    @SerializedName("change")
    @Expose
    var change: Double? = null

    @SerializedName("pChange")
    @Expose
    var pChange: Double? = null

    @SerializedName("totalTradedVolume")
    @Expose
    var totalTradedVolume: Int? = null

    @SerializedName("totalTradedValue")
    @Expose
    var totalTradedValue: Double? = null

    @SerializedName("lastUpdateTime")
    @Expose
    var lastUpdateTime: String? = null

    @SerializedName("yearHigh")
    @Expose
    var yearHigh: Double? = null

    @SerializedName("ffmc")
    @Expose
    var ffmc: Double? = null

    @SerializedName("yearLow")
    @Expose
    var yearLow: Double? = null

    @SerializedName("nearWKH")
    @Expose
    var nearWKH: Double? = null

    @SerializedName("nearWKL")
    @Expose
    var nearWKL: Double? = null

    @SerializedName("perChange365d")
    @Expose
    var perChange365d: Double? = null

    @SerializedName("date365dAgo")
    @Expose
    var date365dAgo: String? = null

    @SerializedName("chart365dPath")
    @Expose
    var chart365dPath: String? = null

    @SerializedName("date30dAgo")
    @Expose
    var date30dAgo: String? = null

    @SerializedName("perChange30d")
    @Expose
    var perChange30d: Double? = null

    @SerializedName("chart30dPath")
    @Expose
    var chart30dPath: String? = null

    @SerializedName("chartTodayPath")
    @Expose
    var chartTodayPath: String? = null

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null

}