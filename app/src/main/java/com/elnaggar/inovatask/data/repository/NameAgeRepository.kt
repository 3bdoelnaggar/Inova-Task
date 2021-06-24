package com.elnaggar.inovatask.data.repository

import com.elnaggar.inovatask.data.entity.NameAge
import com.elnaggar.inovatask.data.local.NameAgeLocalDataSource
import com.elnaggar.inovatask.data.remote.NameAgeRemoteDataSource
import dagger.Provides
import javax.inject.Inject


class NameAgeRepository @Inject constructor(
    private val remoteDataSource: NameAgeRemoteDataSource,
    private val localDataSource: NameAgeLocalDataSource
) {

    suspend fun getNameAgeList(): List<NameAge> {
        val response = remoteDataSource.fetchNameAgeList()
        localDataSource.addNameAgeList(response.data)
        return localDataSource.getNameAgeList()
    }

}