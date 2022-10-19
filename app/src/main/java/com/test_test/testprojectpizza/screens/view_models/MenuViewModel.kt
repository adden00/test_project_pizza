package com.test_test.testprojectpizza.screens.view_models


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test_test.testprojectpizza.data.MenuItem
import com.test_test.testprojectpizza.retrofit.ApiService
import com.test_test.testprojectpizza.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class MenuViewModel : ViewModel() {
    val menuItemModel = MutableLiveData<List<MenuItem>>()
    val isLoading = MutableLiveData<Boolean>()

    private val api = RetrofitInstance.getInstance().create(ApiService::class.java)


    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = receiveData()
            if (!result.isNullOrEmpty()) {
                menuItemModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    suspend fun receiveData(): List<MenuItem>? {
        var resultList: List<MenuItem>? = null
        try {
            val response = api.getAll()
            resultList = response.body()
        }
        catch (e: Exception) {
        }

        return resultList
    }

}