package com.example.qnachlocal.data.data.dto.verifyotp

data class VerifyOtpRequest(
    val email_id:String,
    val mobile_no: String,
    val otp: String,
    val password: String,
    val confirm_password: String
)