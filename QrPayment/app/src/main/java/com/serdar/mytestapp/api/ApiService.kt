package com.serdar.mytestapp.api

import com.serdar.mytestapp.data.GetQrSaleRequest
import com.serdar.mytestapp.data.GetQrSaleResponse
import com.serdar.mytestapp.data.QrPaymentRequest
import com.serdar.mytestapp.data.QrPaymentResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("get_qr_sale")
    fun getQrSale(@Body request: GetQrSaleRequest): Call<GetQrSaleResponse>

    @POST("payment")
    fun doPayment(@Body request: QrPaymentRequest): Call<QrPaymentResponse>

}