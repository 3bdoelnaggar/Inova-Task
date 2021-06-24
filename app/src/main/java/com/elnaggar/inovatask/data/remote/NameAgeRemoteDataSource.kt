package com.elnaggar.inovatask.data.remote

import com.elnaggar.inovatask.data.remote.response.NameAgeListResponse
import com.google.gson.Gson
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class NameAgeRemoteDataSource @Inject constructor(){
    private val response = "{data:[{name:\"Abdalla\",age:28}," +
            "{name:\"Abdalla\",age:28}," +
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