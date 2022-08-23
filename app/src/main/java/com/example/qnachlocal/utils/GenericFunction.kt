package com.example.qnachlocal.utils

import android.util.Patterns
import java.util.regex.Pattern

var emailPatterns: Pattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
var passwordPattern: Pattern = Pattern.compile("[“^”]" + "[“(?=.*[@#$%^&+=])”]" + "[“(?=\\S+$)”]" + "[“.{8,}”]" + "[“$”]")

fun isValidPhoneNumber(phone: String): Boolean {
    return if (phone.trim { it <= ' ' } != "" && phone.length >= 10) {
        Patterns.PHONE.matcher(phone).matches()
    } else false
}

fun isValidEmailAddress(mail: String): Boolean {
    return emailPatterns.matcher(mail).matches()
}

fun isValidPassword(password:String):Boolean{
    return passwordPattern.matcher(password).matches()
}