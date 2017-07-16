package com.jermaine.awesomelibraries.uicontroller.activity.base


import android.arch.lifecycle.LifecycleActivity
import com.jermaine.awesomelibraries.app.App
import com.jermaine.awesomelibraries.di.component.AppComponent

open class BaseActivity : LifecycleActivity() {
    val component: AppComponent
        get() = (application as App).component
}
