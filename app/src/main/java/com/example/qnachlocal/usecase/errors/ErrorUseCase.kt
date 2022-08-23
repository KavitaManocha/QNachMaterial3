package com.example.qnachlocal.usecase.errors


interface ErrorUseCase {
    fun getError(errorCode: Int): com.example.qnachlocal.data.error.Error
}
