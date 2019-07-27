package com.serdar.mytestapp.qrpayment.payment

import dagger.Module
import dagger.Provides

@Module
class QrPaymentModule {
    @Provides
    fun providePageViewModel() = QrPaymentViewModel()
}