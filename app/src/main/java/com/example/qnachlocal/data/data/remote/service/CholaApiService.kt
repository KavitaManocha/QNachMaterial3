package com.chola.app.data.remote.service

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Sunil
 */

interface CholaApiService {
    @POST("Users/login")
    suspend fun login(
        @Body lRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("Users/resetPassword")
    suspend fun resetPassword(
        @Body lRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse>


    @POST("NachMandate/mandateList")
    suspend fun mandateList(
        @Body mandateRequest: MandateRequest
    ): Response<MandateResponse>

    @POST("NachMandate/userMandateList")
    suspend fun reportList(
        @Body mandateRequest: MandateRequest
    ): Response<MandateResponse>

    @POST("NachMandate/childMandateList")
    suspend fun childMandateList(
        @Body mandateRequest: MandateRequest
    ): Response<MandateResponse>

    @POST("NachMandate/auditLog")
    suspend fun auditTrialList(
        @Body mandateRequest: AuditTrailRequest
    ): Response<MandateResponse>

    @POST("nachMandate/parentLink")
    suspend fun mandateLink(
        @Body mandateRequest: LinkMandateRequest
    ): Response<LinkMandateResponse>


    @GET("NachMandate/searchMandate")
    suspend fun searchMandate(
        @Query("userId") userId:String,
        @Query("id") id:String,
        @Query("searchValue") responseType:String
    ): Response<MandateResponse>

    @POST("QNachApp/forgotPassword")
    suspend fun forgotPassword(
        @Body forgotpasswordrequest: ForgotPasswordRequest
    ): Response<ResetPasswordResponse>

    @POST("Users/verifyOTP")
    suspend fun verifyOtp(
        @Body verifyOtpRequest: VerifyOtpRequest
    ): Response<ResetPasswordResponse>

}