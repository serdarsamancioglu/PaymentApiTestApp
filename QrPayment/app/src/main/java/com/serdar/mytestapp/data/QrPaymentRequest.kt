package com.serdar.mytestapp.data

import com.serdar.mytestapp.entity.PaymentInfo

class QrPaymentRequest {
    var returnCode = 0
    var returnDesc = ""
    var receiptMsgCustomer = "token Campaign"
    var receiptMsgMerchant = "token Campaign Merchant"
    var paymentInfoList: List<PaymentInfo>? = null
    var QRdata = ""
}