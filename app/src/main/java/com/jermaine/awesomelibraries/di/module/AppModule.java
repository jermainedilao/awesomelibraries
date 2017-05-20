package com.jermaine.awesomelibraries.di.module;


import android.app.Application;

import com.google.gson.Gson;
import com.jermaine.awesomelibraries.api.ApiService;
import com.jermaine.awesomelibraries.app.App;
import com.jermaine.awesomelibraries.repository.local.RepositoryLocalImpl;
import com.jermaine.awesomelibraries.repository.server.RepositoryServerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    public RepositoryLocalImpl providesRetrofitRepositoryLocal() {
        return new RepositoryLocalImpl(((App) mApplication).getComponent());
    }

    @Provides
    @Singleton
    public RepositoryServerImpl providesRetrofitRepositoryServer() {
        return new RepositoryServerImpl(((App) mApplication).getComponent());
    }

    @Provides
    @Singleton
    public ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public Realm providesRealmInstance() {
        return Realm.getDefaultInstance();
    }
}
