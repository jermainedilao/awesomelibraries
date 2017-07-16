package com.jermaine.awesomelibraries.app


import android.app.Application

import com.jermaine.awesomelibraries.di.component.AppComponent
import com.jermaine.awesomelibraries.di.component.DaggerAppComponent
import com.jermaine.awesomelibraries.di.module.AppModule

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}