package com.example.qnachlocal.data.remote.cryptology

data class Payload(
    val nonce: String,
    val authTag: String,
    val ciphertext: String
)