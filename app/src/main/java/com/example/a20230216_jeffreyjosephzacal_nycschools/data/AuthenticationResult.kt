package com.example.a20230216_jeffreyjosephzacal_nycschools.data

enum class AuthenticationResult(val errorCode : Int = Int.MIN_VALUE, val errString : String = "") {
    AuthenticationError(), // unrecoverable error has been encountered and the operation is complete
    AuthenticationFailed(), // biometric is valid but not recognized
    AuthenticationHelp(), // recoverable error has been encountered during authentication
    AuthenticationSucceeded() // biometric is recognized
}