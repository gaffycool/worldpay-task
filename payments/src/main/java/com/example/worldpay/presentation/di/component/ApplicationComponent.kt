package com.example.worldpay.presentation.di.component

import android.app.Application
import android.content.Context
import com.example.worldpay.presentation.di.ApplicationContext
import com.brighterbrain.project0.di.module.ApplicationModule
import com.example.worldpay.MainApplication
import com.example.worldpay.domain.repository.PaymentRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun inject(application: MainApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

    fun getPaymentRepository(): PaymentRepository
}