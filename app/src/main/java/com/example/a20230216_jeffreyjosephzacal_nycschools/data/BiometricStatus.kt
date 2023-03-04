package com.example.a20230216_jeffreyjosephzacal_nycschools.data

enum class BiometricStatus (val prompt : String) {
    BIOMETRIC_UNKNOWN("not set"),
    BIOMETRIC_SUCCESS("Authenticated"),
    BIOMETRIC_ERROR_NO_HARDWARE("Device does not support biometics"),
    BIOMETRIC_ERROR_HW_UNAVAILABLE("Biometric device is not available"),
    BIOMETRIC_ERROR_NONE_ENROLLED("biometrics not enabled")
}