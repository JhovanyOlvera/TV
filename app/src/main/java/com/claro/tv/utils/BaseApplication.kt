package com.claro.tv.utils

import android.app.Application
import com.claro.tv.di.DaggerRetrofitComponent
import com.claro.tv.di.RetrofitComponent
import com.claro.tv.di.RetrofitModule

class BaseApplication : Application() {

    lateinit var retrofitComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        retrofitComponent = DaggerRetrofitComponent
            .builder()
            .retrofitModule(RetrofitModule())
            .build()
    }
}