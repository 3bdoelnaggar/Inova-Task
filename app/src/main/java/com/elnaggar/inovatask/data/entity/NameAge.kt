package com.elnaggar.inovatask.data.entity


import com.google.gson.annotations.SerializedName

data class NameAge(
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int

)