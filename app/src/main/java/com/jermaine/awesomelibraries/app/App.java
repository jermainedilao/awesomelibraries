package com.jermaine.awesomelibraries.app;


import android.app.Application;

import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.di.component.DaggerAppComponent;
import com.jermaine.awesomelibraries.di.module.AppModule;

import io.realm.Realm;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        Realm.init(this);
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }
}