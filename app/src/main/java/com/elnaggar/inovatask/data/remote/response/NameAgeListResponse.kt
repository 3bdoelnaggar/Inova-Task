package com.elnaggar.inovatask.data.remote.response


import com.elnaggar.inovatask.data.entity.NameAge
import com.google.gson.annotations.SerializedName

data class NameAgeListResponse(
    @SerializedName("data")
    val `data`: List<NameAge>
)