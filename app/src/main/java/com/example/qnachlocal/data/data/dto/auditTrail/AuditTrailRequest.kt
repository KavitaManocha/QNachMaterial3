package com.chola.app.data.dto.auditTrail

data class AuditTrailRequest(
    val org_id:String,
    val user_id:String,
    val mandate_id:String
)