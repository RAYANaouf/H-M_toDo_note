package com.jetapptech.halfwarenote

import android.app.Application
import com.jetapptech.halfwarenote.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class KoinApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(koinModule)
        }

    }
}