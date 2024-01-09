package com.braincorp.githandbook.core.di

import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.core.ads.AdLoaderImpl
import com.braincorp.githandbook.core.consent.UserConsentManager
import com.braincorp.githandbook.core.consent.UserConsentManagerImpl
import com.braincorp.githandbook.core.dialogue.DialogueHelper
import com.braincorp.githandbook.core.dialogue.DialogueHelperImpl
import com.braincorp.githandbook.core.web.WebPageViewer
import com.braincorp.githandbook.core.web.WebPageViewerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class CoreToolsModule {

    @Binds
    @ActivityScoped
    abstract fun bindUserConsentManager(
        impl: UserConsentManagerImpl
    ): UserConsentManager

    @Binds
    @ActivityScoped
    abstract fun bindAdLoader(impl: AdLoaderImpl): AdLoader

    @Binds
    @ActivityScoped
    abstract fun bindDialogueHelper(impl: DialogueHelperImpl): DialogueHelper

    @Binds
    @ActivityScoped
    abstract fun bindWebPageViewer(impl: WebPageViewerImpl): WebPageViewer
}
