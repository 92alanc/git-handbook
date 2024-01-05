package com.braincorp.githandbook.consent

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean

private const val TAG = "LOG_ALAN"

class UserConsentManagerImpl (private val context: Context) : UserConsentManager {

    private val consentInformation = UserMessagingPlatform.getConsentInformation(context)
    private val hasInitialisedMobileAds = AtomicBoolean(false)

    override fun getConsentIfRequired(activity: AppCompatActivity, onDismiss: () -> Unit) {
        val debugSettings = ConsentDebugSettings.Builder(context)
            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
            .addTestDeviceHashedId("129D666BF112594B64904705F0AFEFB5")
            .build()

        val params = ConsentRequestParameters.Builder()
            .setConsentDebugSettings(debugSettings)
            .build()

        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
                    formError?.let { Log.d(TAG, it.message) }

                    if (consentInformation.canRequestAds()) {
                        initialiseMobileAds()
                        onDismiss()
                    }
                }
            },
            { formError ->
                Log.d(TAG, formError.message)
            }
        )

        if (consentInformation.canRequestAds()) {
            initialiseMobileAds()
            onDismiss()
        }
    }

    override fun isPrivacyOptionsRequired(): Boolean {
        val status = consentInformation.privacyOptionsRequirementStatus
        return status == ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
    }

    override fun showPrivacyOptions(activity: AppCompatActivity) {
        UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
            formError?.let { Log.d(TAG, it.message) }
        }
    }

    private fun initialiseMobileAds() {
        if (!hasInitialisedMobileAds.getAndSet(true)) {
            MobileAds.initialize(context)
        }
    }
}
