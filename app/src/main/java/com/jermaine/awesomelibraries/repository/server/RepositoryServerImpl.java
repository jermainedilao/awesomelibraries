package com.jermaine.awesomelibraries.repository.server;


import com.jermaine.awesomelibraries.api.ApiService;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.RetrofitRepository;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;
import com.jermaine.awesomelibraries.repository.local.RepositoryLocalImpl;

import java.util.List;

import javax.inject.Inject;

import io.realm.OrderedRealmCollectionChangeListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryServerImpl implements RetrofitRepository {

    @Inject
    transient ApiService mApiService;
    @Inject
    transient RepositoryLocalImpl mRetrofitRepositoryLocal;

    public RepositoryServerImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void save(Repo repo) {
        // network request
    }

    @Override
    public void save(List<Repo> repoList) {
        // network request
    }

    @Override
    public void fetchRepos(final OnFetchReposCallback onFetchReposCallback) {
        Call<List<Repo>> call = mApiService.getRepos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repoList = response.body();

                onFetchReposCallback.onSuccess();
                mRetrofitRepositoryLocal.save(repoList);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                onFetchReposCallback.onError();
            }
        });
    }

    @Override
    public void subscribe(OrderedRealmCollectionChangeListener orderedRealmCollectionChangeListener) {
        // leave it blank. implementation on local repository
    }

    @Override
    public void unsubscribe() {
        // leave it blank. implementation on local repository
    }
}
