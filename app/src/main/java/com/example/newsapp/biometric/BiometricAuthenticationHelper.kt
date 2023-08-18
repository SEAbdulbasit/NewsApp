package com.example.newsapp.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.newsapp.R


/**
 * Created by abdulbasit on 18/08/2023.
 */

class BiometricAuthenticationHelper(private val context: Context) {

    fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    @Composable
    fun showBiometricPrompt(
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        onAuthenticationError: (Int, CharSequence) -> Unit
    ) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(stringResource(R.string.biometric_authentication))
            .setSubtitle(stringResource(R.string.authenticate_using_your_biometric_data))
            .setNegativeButtonText(stringResource(R.string.cancel))
            .build()
        val context = LocalContext.current as FragmentActivity

        val biometricPrompt = BiometricPrompt(
            context,
            ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    onFailure()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onAuthenticationError(errorCode, errString)
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }
}

enum class BiometricState {
    UNINITIALIZED,
    AUTH_SUCCESS,
    AUTH_FAILED,
    AUTH_ERROR
}
