package com.serdar.mytestapp.dagger

import com.serdar.mytestapp.qrpayment.payment.QrPaymentFragment
import com.serdar.mytestapp.qrpayment.payment.QrPaymentModule
import com.serdar.mytestapp.qrpayment.result.ResultFragment
import com.serdar.mytestapp.qrpayment.result.ResultModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [QrPaymentModule::class])
    abstract fun providePlaceholderFragment(): QrPaymentFragment

    @ContributesAndroidInjector(modules = [ResultModule::class])
    abstract fun provideResultFragment(): ResultFragment
}