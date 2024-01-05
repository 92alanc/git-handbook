package com.braincorp.githandbook.consent

import androidx.appcompat.app.AppCompatActivity

interface UserConsentManager {

    fun getConsentIfRequired(activity: AppCompatActivity, onDismiss: () -> Unit)

    fun isPrivacyOptionsRequired(): Boolean

    fun showPrivacyOptions(activity: AppCompatActivity)
}
