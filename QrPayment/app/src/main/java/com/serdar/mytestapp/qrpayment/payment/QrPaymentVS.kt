package com.serdar.mytestapp.qrpayment.payment

import com.serdar.mytestapp.data.QrPaymentResponse
import com.serdar.mytestapp.interfaces.IViewState

sealed class QrPaymentVS: IViewState {
    class Validate: QrPaymentVS()
    class ShowProgress(val show: Boolean): QrPaymentVS()
    class PaymentSuccess(val response: QrPaymentResponse): QrPaymentVS()
}