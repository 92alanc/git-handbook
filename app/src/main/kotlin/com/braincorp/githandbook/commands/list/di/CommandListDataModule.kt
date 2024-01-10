package com.braincorp.githandbook.commands.list.di

import com.braincorp.githandbook.core.database.DatabaseProvider
import com.braincorp.githandbook.commands.list.data.database.CommandDao
import com.braincorp.githandbook.commands.list.data.database.CommandDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommandListDataModule {

    @Provides
    @Singleton
    fun provideCommandDatabase(databaseProvider: DatabaseProvider): CommandDatabase {
        return databaseProvider.provideDatabaseFromAsset(
            clazz = CommandDatabase::class,
            name = "commands-db",
            assetName = "database.db"
        )
    }

    @Provides
    @Singleton
    fun provideCommandDao(database: CommandDatabase): CommandDao {
        return database.commandDao()
    }
}
