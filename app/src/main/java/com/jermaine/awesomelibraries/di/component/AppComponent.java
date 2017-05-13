package com.jermaine.awesomelibraries.di.component;

import com.jermaine.awesomelibraries.di.module.AppModule;
import com.jermaine.awesomelibraries.repository.local.RetrofitRepositoryLocalImpl;
import com.jermaine.awesomelibraries.repository.server.RetrofitRepositoryServerImpl;
import com.jermaine.awesomelibraries.view.activity.repolist.RepoListPresenterImpl;
import com.jermaine.awesomelibraries.view.activity.viewitem.ViewItemPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(RepoListPresenterImpl repoListPresenter);

    void inject(RetrofitRepositoryLocalImpl retrofitRepositoryLocal);

    void inject(RetrofitRepositoryServerImpl retrofitRepositoryServer);

    void inject(ViewItemPresenterImpl viewItemPresenter);
}