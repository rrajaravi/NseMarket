package com.example.nsemarket.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Advance {
    @SerializedName("declines")
    @Expose
    var declines: String? = null

    @SerializedName("advances")
    @Expose
    var advances: String? = null

    @SerializedName("unchanged")
    @Expose
    var unchanged: String? = null

}