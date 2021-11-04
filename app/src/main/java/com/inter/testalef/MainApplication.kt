package com.inter.testalef

import android.app.Application
import com.inter.testalef.MainActivity_MembersInjector.create
import com.inter.testalef.di.component.AppComponent
import com.inter.testalef.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject


class MainApplication : Application(), HasAndroidInjector {

    companion object {
        lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var hasAndroidInjector: DispatchingAndroidInjector<Any>

    private fun initAppComponent() {
        appComponent =
            DaggerAppComponent
                .builder()
                .setApplication(this)
                .build()

    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        Timber.plant(Timber.DebugTree())
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = hasAndroidInjector
}