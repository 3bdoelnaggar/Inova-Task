package com.elnaggar.inovatask.data.local

import com.elnaggar.inovatask.data.entity.NameAge
import javax.inject.Inject

class NameAgeLocalDataSource @Inject constructor() {
    private val inMemoryCacheList = mutableListOf<NameAge>()
    suspend fun addNameAgeList(list: List<NameAge>) {
        inMemoryCacheList.addAll(list)
    }
    suspend fun getNameAgeList(): List<NameAge> {
        return inMemoryCacheList
    }
}