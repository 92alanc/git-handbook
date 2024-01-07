package com.braincorp.githandbook.commands.di

import com.braincorp.githandbook.commands.data.mapping.DataToDomainMapper
import com.braincorp.githandbook.commands.data.mapping.DataToDomainMapperImpl
import com.braincorp.githandbook.commands.data.repository.CommandRepositoryImpl
import com.braincorp.githandbook.commands.domain.repository.CommandRepository
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

    @Binds
    @ViewModelScoped
    abstract fun bindDataToDomainMapper(impl: DataToDomainMapperImpl): DataToDomainMapper
}
