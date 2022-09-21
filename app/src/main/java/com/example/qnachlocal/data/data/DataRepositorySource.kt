package com.chola.app.data

import com.chola.app.data.dto.auditTrail.AuditTrailRequest
import com.chola.app.data.dto.linkmandate.LinkMandateRequest
import com.chola.app.data.dto.linkmandate.LinkMandateResponse
import com.chola.app.data.dto.login.LoginRequest
import com.chola.app.data.dto.login.LoginResponse
import com.chola.app.data.dto.mandate.MandateRequest
import com.chola.app.data.dto.mandate.MandateResponse
import com.chola.app.data.dto.reset.ResetPasswordRequest
import com.chola.app.data.dto.reset.ResetPasswordResponse
import com.example.qnachlocal.data.data.dto.User
import com.example.qnachlocal.data.data.dto.UserReq
import com.example.qnachlocal.data.data.dto.forgotpassword.ForgotPasswordRequest
import com.example.qnachlocal.data.data.dto.verifyotp.VerifyOtpRequest
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sunil
 */

interface DataRepositorySource {

    // For Login
    suspend fun doRemoteLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>

    suspend fun doRemoteResetPassword(loginRequest: ResetPasswordRequest): Flow<Resource<ResetPasswordResponse>>


    // For Mandate Response
    suspend fun doRemoteMandate(loginRequest: MandateRequest): Flow<Resource<MandateResponse>>

    // For Report Response
    suspend fun doRemoteReportMandate(loginRequest: MandateRequest): Flow<Resource<MandateResponse>>

    // For Mandate Response
    suspend fun doRemoteChildMandate(loginRequest: MandateRequest): Flow<Resource<MandateResponse>>

    // For Mandate Response
    suspend fun doAuditTrialMandate(loginRequest: AuditTrailRequest): Flow<Resource<MandateResponse>>

    // For Link Mandate
    suspend fun doRemoteLinkMandate(linkMandateRequest: LinkMandateRequest): Flow<Resource<LinkMandateResponse>>

    // For Forgot Password
    suspend fun doForgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Flow<Resource<ResetPasswordResponse>>

    // For Verifying Otp
    suspend fun doVerifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<ResetPasswordResponse>>

    // For Search Mandate
    suspend fun doRemoteSearchMandate(orgId:String,id:String,query: String): Flow<Resource<MandateResponse>>


}