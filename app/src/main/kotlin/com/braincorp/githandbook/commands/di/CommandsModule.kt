package com.braincorp.githandbook.commands.di

import com.braincorp.githandbook.commands.domain.CommandRepository
import com.braincorp.githandbook.commands.data.repository.CommandRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CommandsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCommandRepository(impl: CommandRepositoryImpl): CommandRepository
}
