package com.elnaggar.inovatask.ui.nameAgeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnaggar.inovatask.data.entity.NameAge
import com.elnaggar.inovatask.data.repository.NameAgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameAgeListViewModel @Inject constructor(private val nameAgeRepository: NameAgeRepository) :
    ViewModel() {

    private val _stateLiveData = MutableLiveData<NameAgeListState>()
    val stateLiveData: LiveData<NameAgeListState> = _stateLiveData

    fun getNameAgeList() {
        viewModelScope.launch {
            try {
                val result = nameAgeRepository.getNameAgeList()
                val uiList = result.map {
                    it.toUiNameAge()
                }
                _stateLiveData.value = NameAgeListState.Success(uiList)
            } catch (exception: Exception) {
                exception
                _stateLiveData.value = NameAgeListState.Error
            }

        }
    }

}



private fun NameAge.toUiNameAge(): UiNameAge {
    val uiName = "Name: $name"
    val uiAge = "Age: $age"
    return UiNameAge(uiName, uiAge)
}

sealed class NameAgeListState {
    data class Success(val data: List<UiNameAge>) : NameAgeListState()
    object Loading : NameAgeListState()
    object Error : NameAgeListState()
}

data class UiNameAge(val name: String, val age: String)

