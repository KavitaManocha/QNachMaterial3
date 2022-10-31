package com.example.qnachlocal.data.remote

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.Resource
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.data.dto.ReportResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest

/**
 * Created by Sunil
 */

internal interface RemoteDataSource {
    suspend fun doRemoteDecrypt(
        loginRequest: LoginRequest
    ): Resource<LoginResponse>

suspend fun doForgotPassword(loginRequest:ForgotPasswordRequest): Resource<ResetPasswordResponse>

suspend fun doVerifyOtp(loginRequest:VerifyOtpRequest): Resource<ResetPasswordResponse>

suspend fun doGeneratePdf(user: Usser): Resource<PDFResponse>

suspend fun doRequestReport(requestReport: REquestReport): Resource<ReportResponse>

}