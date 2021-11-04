package com.inter.testalef.di.component

import android.app.Application
import androidx.fragment.app.Fragment
import com.inter.testalef.MainActivity
import com.inter.testalef.MainApplication
import com.inter.testalef.di.modules.*
import com.inter.testalef.ui.fragments.MainFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        NetworkModule::class,
        AppModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        UseCaseModule::class
    ]
)

@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setApplication(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: MainFragment)
}