package com.quannv.searchapplication.component

import android.app.Application
import com.quannv.searchapplication.CustomApplication
import com.quannv.searchapplication.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent  {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CustomApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: Application)
}
