package com.chola.app.data.dto.linkmandate

data class LinkMandateRequest(
    val `data`: ChildLinkMandate,
    val org_id: String,
    val user_id: String,
)