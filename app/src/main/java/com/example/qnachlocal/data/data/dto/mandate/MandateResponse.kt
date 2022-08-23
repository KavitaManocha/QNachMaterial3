package com.chola.app.data.dto.mandate

import java.io.Serializable

data class MandateResponse(
    val StatusCode: String,
    val StatusDesc: String,
    val total_pages: Int,
    val total_records: String,
    val page: String,
    val `data`: ArrayList<MandateList>
):Serializable