package com.jetapptech.halfwarenote.di

import androidx.room.Room
import com.jetapptech.halfwarenote.data.local.room.database.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val koinModule = module{

    single {
        Room
            .databaseBuilder(
                context = androidContext(),
                klass   = Database::class.java,
                name    = "myDatabaseV11"
            )
//            .addTypeConverter(Converter_Room())
            .fallbackToDestructiveMigration()
            .build()
    }

}