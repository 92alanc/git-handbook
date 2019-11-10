package com.braincorp.githandbook

import com.braincorp.githandbook.database.CommandDao
import com.braincorp.githandbook.database.CommandDatabase
import com.braincorp.githandbook.repository.CommandRepository
import com.nhaarman.mockitokotlin2.mock
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun startDependencyInjection() {
    startKoin {
        modules(
                listOf(
                        databaseModule(),
                        repositoryModule()
                )
        )
    }
}

private fun databaseModule() = module {
    single { mock<CommandDatabase>() }
    single { mock<CommandDao>() }
}

private fun repositoryModule() = module {
    factory { mock<CommandRepository>() }
}