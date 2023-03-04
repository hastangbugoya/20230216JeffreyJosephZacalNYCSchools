package com.example.a20230216_jeffreyjosephzacal_nycschools.security

import android.content.Context
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AlertType
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.AuthenticationResult
import com.example.a20230216_jeffreyjosephzacal_nycschools.data.BiometricStatus

object AuthenticateFingerprint {
    private var uiUpdate : UIUpdate?  = null
    fun Authenticate(activity : FragmentActivity) {
        if (checkDeviceHasBiometric(activity.applicationContext)){
            val executor = ContextCompat.getMainExecutor(activity)

            val callback = object: BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Log.d("AuthenticateFingerprint","$errorCode :: $errString")
                    uiUpdate?.updateAAuthenticationStatus(BiometricStatus.BIOMETRIC_UNKNOWN)
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.d("AuthenticateFingerprint","Authentication failed")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.d("AuthenticateFingerprint","Authentication was successful")
                }
            }

            val biometricPrompt = BiometricPrompt(
                activity,
                executor,
                callback
            )
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setDescription("User needs to be authenticated before using the app")
                .setDeviceCredentialAllowed(true)
                .build()

            biometricPrompt.authenticate(promptInfo)
        }
    }
    fun checkDeviceHasBiometric(context: Context) : Boolean  = BiometricManager.from(context).canAuthenticate() == BIOMETRIC_SUCCESS

    fun setUIUpdate(uiu : UIUpdate) {
        uiUpdate = uiu
    }

    interface UIUpdate {
        fun displayAlert(s : String, type : AlertType)
        fun updateAAuthenticationStatus(status : BiometricStatus)
        fun displayAuthenticationResult(result : AuthenticationResult)
    }
}

