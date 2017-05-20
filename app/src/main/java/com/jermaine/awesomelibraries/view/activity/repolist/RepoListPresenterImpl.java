package com.jermaine.awesomelibraries.view.activity.repolist;


import android.util.Log;

import com.google.gson.Gson;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.base.BaseView;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;
import com.jermaine.awesomelibraries.repository.local.RepositoryLocalImpl;
import com.jermaine.awesomelibraries.repository.server.RepositoryServerImpl;

import javax.inject.Inject;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

public class RepoListPresenterImpl implements RepoListContract.RepoListPresenter {

    public static final String TAG = "RepoListPresenter";

    @Inject
    transient RepositoryLocalImpl mRetrofitRepositoryLocal;
    @Inject
    transient RepositoryServerImpl mRetrofitRepositoryServer;
    @Inject
    transient Gson mGson;

    private RepoListContract.RepoListView mView;

    public RepoListPresenterImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(BaseView view) {
        mView = (RepoListContract.RepoListView) view;
        mRetrofitRepositoryLocal.subscribe(new OrderedRealmCollectionChangeListener<RealmResults<Repo>>() {
            @Override
            public void onChange(RealmResults<Repo> collection, OrderedCollectionChangeSet changeSet) {
                mView.provideRepos(collection);
            }
        });

        fetchServer();
    }

    @Override
    public void onStop() {
        mRetrofitRepositoryLocal.unsubscribe();
    }

    @Override
    public void fetchServer() {
        mView.showProgressDialog();
        mRetrofitRepositoryServer.fetchRepos(new OnFetchReposCallback() {
            @Override
            public void onSuccess() {
                mView.dismissProgressDialog();
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError: ");
            }
        });
    }

    @Override
    public void onItemClick(Repo repo) {
        mView.startViewItemScreen(mGson.toJson(repo));
    }
}
