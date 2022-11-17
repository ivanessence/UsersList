package com.ivan.essence.userslist

import android.app.Application
import com.ivan.essence.userslist.di.networkModule
import com.ivan.essence.userslist.di.repositoriesModule
import com.ivan.essence.userslist.di.useCasesModule
import com.ivan.essence.userslist.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class UsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@UsersApplication)
            modules(
                listOf(networkModule, viewModelsModule, repositoriesModule, useCasesModule)
            )
        }
    }
}