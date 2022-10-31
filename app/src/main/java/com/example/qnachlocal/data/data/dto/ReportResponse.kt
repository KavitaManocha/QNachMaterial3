package com.example.qnachlocal.data.data.dto

data class ReportResponse(
    var name:String,
    var cust_id: String,
    var ach_amt: String,
    var nupay_ref_no: String,
    var sales_query_code:String,
    var date: String,
    var reason: String,
    var img_url: String,
    var StatusCode: String,
    var StatusDesc: String
)