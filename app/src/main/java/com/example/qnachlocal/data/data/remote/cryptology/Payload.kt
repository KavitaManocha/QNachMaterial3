package com.chola.app.data.remote.cryptology

data class Payload(
    val nonce: String,
    val authTag: String,
    val ciphertext: String
)