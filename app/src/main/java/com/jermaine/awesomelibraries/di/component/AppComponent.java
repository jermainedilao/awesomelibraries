package com.jermaine.awesomelibraries.di.component;

import com.jermaine.awesomelibraries.di.module.AppModule;
import com.jermaine.awesomelibraries.repository.local.RepositoryLocalImpl;
import com.jermaine.awesomelibraries.repository.server.RepositoryServerImpl;
import com.jermaine.awesomelibraries.view.activity.additem.AddRepoPresenterImpl;
import com.jermaine.awesomelibraries.view.activity.repolist.RepoListPresenterImpl;
import com.jermaine.awesomelibraries.view.activity.viewitem.ViewItemPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(RepoListPresenterImpl repoListPresenter);

    void inject(RepositoryLocalImpl retrofitRepositoryLocal);

    void inject(RepositoryServerImpl retrofitRepositoryServer);

    void inject(ViewItemPresenterImpl viewItemPresenter);

    void inject(AddRepoPresenterImpl addRepoPresenter);
}