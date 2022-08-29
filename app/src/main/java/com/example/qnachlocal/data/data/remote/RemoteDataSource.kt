package com.chola.app.data.remote

import com.chola.app.data.Resource
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
import retrofit2.http.Query

/**
 * Created by Sunil
 */

internal interface RemoteDataSource {

    // for Login
    suspend fun doRemoteLogin(
        loginRequest: LoginRequest
    ): Resource<LoginResponse>


    // for ResetPassword
    suspend fun doRemoteResetPassword(
        loginRequest: ResetPasswordRequest
    ): Resource<ResetPasswordResponse>


    // for mandateResponse
    suspend fun doRemoteMandateResponse(
        mandate: MandateRequest
    ): Resource<MandateResponse>

    // for mandateResponse
    suspend fun doRemoteChildResponse(
        mandate: MandateRequest
    ): Resource<MandateResponse>

    // for mandateResponse
    suspend fun doAuditTrialResponse(
        mandate: AuditTrailRequest
    ): Resource<MandateResponse>

    // for mandateResponse
    suspend fun doRemoteReportResponse(
        mandate: MandateRequest
    ): Resource<MandateResponse>


    // for mandateResponse
    suspend fun doRemoteLinkMandate(
        mandate: LinkMandateRequest
    ): Resource<LinkMandateResponse>

    suspend fun doForgotPassword(
        mandate: ForgotPasswordRequest
    ):Resource<ResetPasswordResponse>

    // for mandate Search Response
    suspend fun doSearchMandate(
        orgId:String,
        id:String,
        mandate: String
    ): Resource<MandateResponse>

    suspend fun doVerifyOtp(
        mandate: VerifyOtpRequest
    ): Resource<ResetPasswordResponse>}