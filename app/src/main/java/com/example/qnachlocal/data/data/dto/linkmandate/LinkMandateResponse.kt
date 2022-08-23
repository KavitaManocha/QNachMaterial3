package com.chola.app.data.dto.linkmandate

data class LinkMandateResponse(
    val StatusCode: String,
    val StatusDesc: String,
    val childID: String,
    val parentID: Int
)