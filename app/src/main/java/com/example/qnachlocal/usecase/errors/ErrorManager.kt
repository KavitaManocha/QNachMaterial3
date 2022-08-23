package com.example.qnachlocal.usecase.errors

import com.example.qnachlocal.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by Sunil
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): com.example.qnachlocal.data.error.Error {
        return com.example.qnachlocal.data.error.Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
