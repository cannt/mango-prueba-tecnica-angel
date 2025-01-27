package com.devskiller.notepadplus

import android.app.Application
import com.devskiller.notepadplus.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotepadApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NotepadApplication)
            modules(appModule)
        }
    }
}