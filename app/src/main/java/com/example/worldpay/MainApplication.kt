package com.example.worldpay

import android.app.Application
import android.content.Context
import com.brighterbrain.project0.di.module.ApplicationModule
import com.example.worldpay.presentation.di.component.ApplicationComponent
import com.example.worldpay.presentation.di.component.DaggerApplicationComponent

class MainApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().
            applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }
    companion object {
        fun get(context: Context):MainApplication{
            return context.applicationContext as MainApplication
        }
    }
}