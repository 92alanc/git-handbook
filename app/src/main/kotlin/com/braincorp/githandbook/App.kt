package com.braincorp.githandbook

import android.app.Application
import androidx.room.Room
import com.braincorp.githandbook.database.CommandDatabase
import com.braincorp.githandbook.repository.CommandRepository
import com.braincorp.githandbook.repository.CommandRepositoryImpl
import com.braincorp.githandbook.viewmodel.CommandViewModel
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        MobileAds.initialize(this)
        startKoin {
            androidContext(this@App)
            modules(
                    listOf(
                            databaseModule(),
                            repositoryModule(),
                            viewModelModule()
                    )
            )
        }
    }

    private fun databaseModule() = module {
        single {
            Room.databaseBuilder(androidContext(), CommandDatabase::class.java, "commands-db")
                    .createFromAsset("database.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }
        single { get<CommandDatabase>().commandDao() }
    }

    private fun repositoryModule() = module {
        factory<CommandRepository> { CommandRepositoryImpl(get(), get()) }
    }

    private fun viewModelModule() = module {
        viewModel { CommandViewModel(get(), get()) }
    }


}