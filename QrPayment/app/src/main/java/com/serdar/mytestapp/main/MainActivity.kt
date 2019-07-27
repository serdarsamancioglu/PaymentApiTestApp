package com.serdar.mytestapp.main

import android.os.Bundle
import com.serdar.mytestapp.R
import com.serdar.mytestapp.base.BaseActivity
import com.serdar.mytestapp.qrpayment.payment.QrPaymentFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, QrPaymentFragment()).commit()
    }
}