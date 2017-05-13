package com.jermaine.awesomelibraries.repository.local;


import android.util.Log;

import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.RetrofitRepository;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;

import java.util.List;

import javax.inject.Inject;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class RetrofitRepositoryLocalImpl implements RetrofitRepository {

    public static final String TAG = "RetrofitRepositoryLocal";

    @Inject
    transient Realm mRealm;

    public RetrofitRepositoryLocalImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void save(Repo repo) {
        // realm save
    }

    @Override
    public void save(final List<Repo> repoList) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(repoList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: ");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "onError: ", error);
            }
        });
    }

    @Override
    public void fetchRepos(final OnFetchReposCallback onFetchReposCallback) {
        final RealmResults<Repo> repoRealmResults = mRealm.where(Repo.class)
                .findAllAsync();
        repoRealmResults.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Repo>>() {
            @Override
            public void onChange(RealmResults<Repo> collection, OrderedCollectionChangeSet changeSet) {
                onFetchReposCallback.onFetch(collection);
                repoRealmResults.removeAllChangeListeners();
            }
        });
    }
}
