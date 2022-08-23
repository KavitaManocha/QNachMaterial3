package com.chola.app.data.dto.auth

data class Token(
    val tokenType: String,
    val expireIn: Long,
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        val DEBUG = Token("Bearer", 123456789, "accessToken", "refreshToken")
    }
}