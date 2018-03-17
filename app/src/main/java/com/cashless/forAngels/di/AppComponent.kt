package com.cashless.forAngels.di

import com.cashless.forAngels.Application
import com.cashless.forAngels.di.module.ActivityBindingModule
import com.cashless.forAngels.di.module.ApplicationModule
import com.cashless.forAngels.di.module.NetworkModule
import com.cashless.forAngels.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        NetworkModule::class,
        ServiceModule::class))
interface AppComponent : AndroidInjector<Application> {

    override fun inject(app: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}