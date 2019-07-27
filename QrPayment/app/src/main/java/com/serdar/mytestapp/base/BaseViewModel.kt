package com.serdar.mytestapp.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.serdar.mytestapp.api.ApiService
import com.serdar.mytestapp.interfaces.IViewState
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    @Inject
    lateinit var retrofit: Retrofit

    var state = MutableLiveData<IViewState>()

    lateinit var apiService: ApiService

    fun onCreate(retrofit: Retrofit) {
        apiService = retrofit.create(ApiService::class.java)
    }
}