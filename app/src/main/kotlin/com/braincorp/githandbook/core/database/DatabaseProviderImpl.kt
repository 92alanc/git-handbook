package com.braincorp.githandbook.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject
import kotlin.reflect.KClass

class DatabaseProviderImpl @Inject constructor(
    private val context: Context
) : DatabaseProvider {

    override fun <T : RoomDatabase> provideDatabaseFromAsset(
        clazz: KClass<T>,
        name: String,
        assetName: String
    ): T {
        return Room.databaseBuilder(
            context,
            clazz.java,
            name
        ).createFromAsset(assetName)
            .fallbackToDestructiveMigration()
            .build()
    }
}
