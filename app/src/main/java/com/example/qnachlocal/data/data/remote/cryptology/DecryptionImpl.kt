package com.chola.app.data.remote.cryptology

import com.chola.app.data.remote.cryptology.Crypto
import com.chola.app.data.remote.cryptology.CryptoStrategy


class DecryptionImpl : CryptoStrategy {
    override fun encrypt(body: String): String? {
        TODO("Not yet implemented")
    }


    override fun decrypt(data: String): String {
        return Crypto.decrypt(data)
    }
}