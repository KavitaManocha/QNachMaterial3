package com.example.qnachlocal.data.remote.service

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.Usser
import com.example.qnachlocal.data.data.dto.PDFResponse
import com.example.qnachlocal.data.data.dto.REquestReport
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Sunil
 */

interface UgroApiService {

    @POST("NScanApp/login")
    suspend fun login(
        @Body lRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("QNachApp/forgotPassword")
    suspend fun forgotPassword(
        @Body forgotpasswordrequest: ForgotPasswordRequest
    ): Response<ResetPasswordResponse>

    @POST("QNachApp/verifyOTP")
    suspend fun verifyOtp(
        @Body verifyOtpRequest: VerifyOtpRequest
    ): Response<ResetPasswordResponse>

    @POST("QNachApp/createNachPDF")
    suspend fun createPDF(
        @Body user: Usser
    ): Response<PDFResponse>

    @POST("QNachApp/nachPDFList")
    suspend fun viewReport(
        @Body requestReport: REquestReport
    ): Response<PDFResponse>

}



