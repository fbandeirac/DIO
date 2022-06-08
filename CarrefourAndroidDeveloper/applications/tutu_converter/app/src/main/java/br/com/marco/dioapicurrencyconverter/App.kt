package dev.stoneworks.tutu_converter

import android.app.Application
import dev.stoneworks.tutu_converter.data.di.DataModules
import dev.stoneworks.tutu_converter.domain.di.DomainModule
import dev.stoneworks.tutu_converter.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModules.load()
        DomainModule.load()
        PresentationModule.load()
    }
}