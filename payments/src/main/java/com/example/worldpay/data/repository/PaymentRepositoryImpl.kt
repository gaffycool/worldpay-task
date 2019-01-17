package com.example.worldpay.data.repository

import com.example.worldpay.domain.entity.AuthRequest
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import io.reactivex.Single
import javax.inject.Inject

class PaymentRepositoryImpl : PaymentRepository {
    override fun authorizePayment(request: AuthRequest): Single<AuthResponse> {
        return Single.create<AuthResponse> {  }
    }
}