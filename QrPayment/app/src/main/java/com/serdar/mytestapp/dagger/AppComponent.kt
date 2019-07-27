package com.serdar.mytestapp.dagger

import com.serdar.mytestapp.api.RetrofitModule
import com.serdar.mytestapp.main.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (RetrofitModule::class)])
interface AppComponent {

    fun inject(application: MainApplication)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(mainApplication: MainApplication): Builder
    }
}