package com.example.worldpay.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.worldpay.MainApplication
import com.example.worldpay.presentation.di.component.ActivityComponent
import com.example.worldpay.presentation.di.component.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    abstract fun getContentView(): Int

    fun getComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationComponent(
                MainApplication.get(this).applicationComponent
        ).build()
    }
}