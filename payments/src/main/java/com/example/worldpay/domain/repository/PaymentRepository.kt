package com.example.worldpay.domain.repository

import com.example.worldpay.domain.entity.AuthRequest
import com.example.worldpay.domain.entity.AuthResponse
import io.reactivex.Single

interface PaymentRepository {
    fun authorizePayment(request: AuthRequest): Single<AuthResponse>
}