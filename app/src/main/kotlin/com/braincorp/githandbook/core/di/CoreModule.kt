package com.braincorp.githandbook.core.di

import android.content.Context
import com.braincorp.githandbook.core.database.DatabaseProvider
import com.braincorp.githandbook.core.database.DatabaseProviderImpl
import com.braincorp.githandbook.core.log.Logger
import com.braincorp.githandbook.core.log.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideDatabaseProvider(
        @ApplicationContext context: Context
    ): DatabaseProvider = DatabaseProviderImpl(context)

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()
}
