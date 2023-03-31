package br.com.lucas.todo

import android.app.Application
import br.com.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            modules(coreModule)
        }
        Timber.plant(Timber.DebugTree())
    }

}