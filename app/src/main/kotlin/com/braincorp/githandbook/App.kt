package com.braincorp.githandbook

import android.app.Application
import androidx.room.Room
import com.braincorp.githandbook.consent.UserConsentManager
import com.braincorp.githandbook.consent.UserConsentManagerImpl
import com.braincorp.githandbook.database.CommandDatabase
import com.braincorp.githandbook.repository.CommandRepository
import com.braincorp.githandbook.repository.CommandRepositoryImpl
import com.braincorp.githandbook.viewmodel.CommandViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    toolsModule(),
                    databaseModule(),
                    repositoryModule(),
                    viewModelModule()
                )
            )
        }
    }

    private fun toolsModule() = module {
        factory<UserConsentManager> { UserConsentManagerImpl(context = get()) }
    }

    private fun databaseModule() = module {
        single {
            Room.databaseBuilder(
                androidContext(),
                CommandDatabase::class.java,
                "commands-db"
            ).createFromAsset("database.db")
                .fallbackToDestructiveMigration()
                .build()
        }
        factory { get<CommandDatabase>().commandDao() }
    }

    private fun repositoryModule() = module {
        factory<CommandRepository> {
            CommandRepositoryImpl(
                context = get(),
                database = get()
            )
        }
    }

    private fun viewModelModule() = module {
        viewModel { CommandViewModel(repository = get()) }
    }

}