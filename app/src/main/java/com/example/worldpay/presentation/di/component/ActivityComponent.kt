package com.example.worldpay.presentation.di.component

import com.example.worldpay.presentation.di.PerActivity
import com.brighterbrain.project0.di.module.ActivityModule
import com.example.worldpay.presentation.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}