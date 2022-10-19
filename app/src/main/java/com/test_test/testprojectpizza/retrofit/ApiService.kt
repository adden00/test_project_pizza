package com.test_test.testprojectpizza.retrofit
import com.test_test.testprojectpizza.data.MenuItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET (".")
    suspend fun getAll(): Response<List<MenuItem>>
}


