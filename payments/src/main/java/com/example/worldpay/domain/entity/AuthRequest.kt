package com.example.worldpay.domain.entity

data class AuthRequest (
    val cardNo: String,
    val cardHoldersName: String,
    val expMonth: Int,
    val expYear: Int,
    val cvv: Int
)