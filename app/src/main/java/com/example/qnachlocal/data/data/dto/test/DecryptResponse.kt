package com.chola.app.data.dto.test

data class DecryptResponse(
    /*val value: String*/
    val aesCipher_nonce: String,
    val authTag: String,
    val ciphertext: String
)