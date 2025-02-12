package com.example.dailymemo.Service

interface SignUpView {
    fun isNickNameSuccess()
    fun isNickNameFailed()
    fun isEmailSuccess()
    fun isEmailFailed()
    fun emailVerifySuccess(token: String, code: String)
    fun emailVerifyFailed()
    fun verifySuccess()
    fun verifyFailed()
    fun registerSuccess()
    fun registerFailed()
}
