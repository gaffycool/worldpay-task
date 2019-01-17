package com.example.worldpay.domain.usecase

import com.example.worldpay.domain.common.*
import com.example.worldpay.domain.entity.AuthRequest
import com.example.worldpay.domain.entity.AuthResponse
import com.example.worldpay.domain.repository.PaymentRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AuthorizePaymentUseCase @Inject constructor(private val paymentRepo: PaymentRepository) :
    UseCase<AuthResponse>() {
    override fun createObservable(data: Map<String, Any>?): Observable<AuthResponse> {
        when{
            data?.get(CARD_NO)?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, CARD_NO_REQUIRED))
                }.toObservable()
            }
            data[CARD_HOLDER_NAME]?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, CARD_HOLDER_NAME_REQUIRED))
                }.toObservable()
            }
            data[EXPIRY_MONTH]?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, EXPIRY_MONTH_REQUIRED))
                }.toObservable()
            }
            data[EXPIRY_YEAR]?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, EXPIRY_YEAR_REQUIRED))
                }.toObservable()
            }
            data[CVV]?.toString()?.isEmpty()!! ->{
                return Single.create<AuthResponse> {
                    it.onSuccess(AuthResponse(500, CVV_REQUIRED))
                }.toObservable()
            }
        }
        return paymentRepo.authorizePayment(AuthRequest(data?.get(CARD_NO).toString(),
            data?.get(CARD_HOLDER_NAME).toString(),data?.get(EXPIRY_MONTH).toString().toInt(),
            data?.get(EXPIRY_YEAR).toString().toInt(),data?.get(CVV).toString().toInt())).toObservable()
    }
}