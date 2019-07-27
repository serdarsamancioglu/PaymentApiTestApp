package com.serdar.mytestapp.qrpayment.payment

import android.databinding.ObservableField
import com.serdar.mytestapp.base.BaseViewModel
import com.serdar.mytestapp.data.GetQrSaleRequest
import com.serdar.mytestapp.data.GetQrSaleResponse
import com.serdar.mytestapp.data.QrPaymentRequest
import com.serdar.mytestapp.data.QrPaymentResponse
import com.serdar.mytestapp.entity.PaymentAction
import com.serdar.mytestapp.entity.PaymentInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QrPaymentViewModel : BaseViewModel() {

    val qrCode = ObservableField<String>("")
    val amount = ObservableField<String>("")

    fun validate() {
        state.value = QrPaymentVS.Validate()
    }

    fun getQrSale() {
        val request = GetQrSaleRequest()
        request.totalReceiptAmount = amount.get()?.toDoubleOrNull()
        apiService.getQrSale(request)
            .enqueue(object: Callback<GetQrSaleResponse> {
                override fun onResponse(call: Call<GetQrSaleResponse>, response: Response<GetQrSaleResponse>) {
                    response.body()?.let {
                        submit(it)
                    }
                }

                override fun onFailure(call: Call<GetQrSaleResponse>, t: Throwable) {
                    state.value = QrPaymentVS.ShowProgress(false)
                }
            })
    }

    private fun submit(qrSaleResponse: GetQrSaleResponse) {

        val paymentAction = PaymentAction()
        paymentAction.amount = amount.get()?.toDoubleOrNull()

        val paymentInfo = PaymentInfo()
        paymentInfo.paymentActionList = listOf(paymentAction)

        val request = QrPaymentRequest()
        request.returnCode = qrSaleResponse.returnCode
        request.returnDesc = qrSaleResponse.returnDesc
        request.QRdata = qrSaleResponse.QRdata
        request.paymentInfoList = listOf(paymentInfo)

        apiService.doPayment(request)
            .enqueue(object: Callback<QrPaymentResponse> {
                override fun onResponse(call: Call<QrPaymentResponse>, response: Response<QrPaymentResponse>) {
                    state.value = QrPaymentVS.ShowProgress(false)
                    response.body()?.let {
                        state.value = QrPaymentVS.PaymentSuccess(it)
                    }
                }

                override fun onFailure(call: Call<QrPaymentResponse>, t: Throwable) {
                    state.value = QrPaymentVS.ShowProgress(false)
                }
            })
    }
}