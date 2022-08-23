package com.chola.app.data.remote.cryptology

import com.chola.app.data.remote.cryptology.Crypto
import com.chola.app.data.remote.cryptology.CryptoStrategy


class EncryptionImpl : CryptoStrategy {

    override fun encrypt(body: String): String {
        return Crypto.encrypt(body)
    }

    override fun decrypt(data: String): String? {
        TODO("Not yet implemented")
    }

}