package com.elnaggar.inovatask.data.entity


import com.google.gson.annotations.SerializedName

data class NameAge(
    @SerializedName("age")
    val age: Int,
    @SerializedName("name")
    val name: String
)