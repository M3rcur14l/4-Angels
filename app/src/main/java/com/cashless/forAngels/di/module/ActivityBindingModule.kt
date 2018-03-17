package com.cashless.forAngels.di.module

import com.cashless.forAngels.HomeActivity
import com.cashless.forAngels.ScanActivity
import com.cashless.forAngels.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Antonello Fodde on 17/03/18.
 * fodde.antonello@gmail.com
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun scanActivity(): ScanActivity
}