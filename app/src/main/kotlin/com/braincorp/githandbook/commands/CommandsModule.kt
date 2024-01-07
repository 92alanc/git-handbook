package com.braincorp.githandbook.commands

import com.braincorp.githandbook.repository.CommandRepository
import com.braincorp.githandbook.repository.CommandRepositoryImpl
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
