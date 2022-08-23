package com.chola.app.data.dto.mandate

data class MandateRequest(
    val org_id: String,
    val user_id: String,
    val from_date: String,
    val to_date: String,
    val no_of_record: String,
    val page: String,
    val parent_id: String?=null
)