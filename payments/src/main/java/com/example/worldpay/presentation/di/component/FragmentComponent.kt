package com.example.worldpay.presentation.di.component

import com.example.worldpay.presentation.di.PerFragment
import com.brighterbrain.project0.di.module.FragmentModule
import com.example.worldpay.presentation.main.authorizepayment.AuthorizePaymentFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: AuthorizePaymentFragment)
}