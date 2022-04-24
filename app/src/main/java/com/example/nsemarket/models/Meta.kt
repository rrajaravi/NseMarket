package com.example.nsemarket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

    @SerializedName("companyName")
    @Expose
    var companyName: String? = null

    @SerializedName("industry")
    @Expose
    var industry: String? = null

    @SerializedName("activeSeries")
    @Expose
    var activeSeries: List<String>? = null

    @SerializedName("debtSeries")
    @Expose
    var debtSeries: List<String>? = null

    @SerializedName("tempSuspendedSeries")
    @Expose
    var tempSuspendedSeries: List<String>? = null

    @SerializedName("isFNOSec")
    @Expose
    var isFNOSec: Boolean? = null

    @SerializedName("isCASec")
    @Expose
    var isCASec: Boolean? = null

    @SerializedName("isSLBSec")
    @Expose
    var isSLBSec: Boolean? = null

    @SerializedName("isDebtSec")
    @Expose
    var isDebtSec: Boolean? = null

    @SerializedName("isSuspended")
    @Expose
    var isSuspended: Boolean? = null

    @SerializedName("isETFSec")
    @Expose
    var isETFSec: Boolean? = null

    @SerializedName("isDelisted")
    @Expose
    var isDelisted: Boolean? = null

    @SerializedName("isin")
    @Expose
    var isin: String? = null

}