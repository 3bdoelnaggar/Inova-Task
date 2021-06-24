package com.elnaggar.inovatask.data.local

import com.elnaggar.inovatask.data.entity.NameAge

class NameAgeLocalDataSource {
    private val inMemoryCacheList = mutableListOf<NameAge>()
    suspend fun addNameAgeList(list: List<NameAge>) {
        inMemoryCacheList.addAll(list)
    }
    suspend fun getNameAgeList(): MutableList<NameAge> {
        return inMemoryCacheList
    }
}