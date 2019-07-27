package com.serdar.mytestapp.qrpayment.result

import dagger.Module
import dagger.Provides

@Module
class ResultModule {
    @Provides
    fun provideViewModel() = ResultViewModel()
}