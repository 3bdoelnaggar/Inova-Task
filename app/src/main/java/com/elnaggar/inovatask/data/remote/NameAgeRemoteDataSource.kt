package com.elnaggar.inovatask.data.remote

import com.elnaggar.inovatask.data.remote.response.NameAgeListResponse
import com.google.gson.Gson

class NameAgeRemoteDataSource {
    private val response = "{data:[{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28}," +
            "{name:\"abdalla\",age:28},]}"

    suspend fun fetchNameAgeList(): NameAgeListResponse {
        return Gson().fromJson(response,NameAgeListResponse::class.java)
    }
}