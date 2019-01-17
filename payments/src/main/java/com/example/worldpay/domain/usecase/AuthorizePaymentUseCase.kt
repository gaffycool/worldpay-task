package com.example.worldpay.domain.usecase

import com.example.worldpay.domain.common.PAYMENT_REQUEST
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import com.example.worldpay.presentation.entity.PaymentDetail
import io.reactivex.Observable
import javax.inject.Inject

class AuthorizePaymentUseCase @Inject constructor(private val paymentRepo: PaymentRepository): UseCase<AuthResponse>() {
    override fun createObservable(data: Map<String, Any>?): Observable<AuthResponse> {
        val authRequest = (data?.get(PAYMENT_REQUEST) as PaymentDetail).toAuthRequest()
        return paymentRepo.authorizePayment(authRequest).toObservable()
    }
}