package com.chola.app.data.remote.cryptology

interface CryptoStrategy {

    fun encrypt(body: String): String?

    fun decrypt(data: String): String?
}