package com.jermaine.awesomelibraries.view.activity.repolist;


import android.util.Log;

import com.google.gson.Gson;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.base.BaseView;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;
import com.jermaine.awesomelibraries.repository.local.RetrofitRepositoryLocalImpl;
import com.jermaine.awesomelibraries.repository.server.RetrofitRepositoryServerImpl;

import java.util.List;

import javax.inject.Inject;

public class RepoListPresenterImpl implements RepoListContract.RepoListPresenter {

    public static final String TAG = "RepoListPresenter";

    @Inject
    transient RetrofitRepositoryLocalImpl mRetrofitRepositoryLocal;
    @Inject
    transient RetrofitRepositoryServerImpl mRetrofitRepositoryServer;
    @Inject
    transient Gson mGson;

    private RepoListContract.RepoListView mView;

    public RepoListPresenterImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(BaseView view) {
        mView = (RepoListContract.RepoListView) view;

        fetchLocal();
    }

    @Override
    public void fetchLocal() {
        mRetrofitRepositoryLocal.fetchRepos(new OnFetchReposCallback() {
            @Override
            public void onFetch(List<Repo> responseList) {
                mView.provideRepos(responseList);

                fetchServer();
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError: ");
            }
        });
    }

    @Override
    public void fetchServer() {
        mView.showProgressDialog();
        mRetrofitRepositoryServer.fetchRepos(new OnFetchReposCallback() {
            @Override
            public void onFetch(List<Repo> responseList) {
                mView.provideRepos(responseList);
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
