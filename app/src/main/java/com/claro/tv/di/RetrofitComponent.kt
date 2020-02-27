package com.claro.tv.di

import com.claro.tv.ui.DetailActivity
import com.claro.tv.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailActivity: DetailActivity)
}