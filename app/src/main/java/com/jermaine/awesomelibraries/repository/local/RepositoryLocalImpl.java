package com.jermaine.awesomelibraries.repository.local;


import android.util.Log;

import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.RetrofitRepository;
import com.jermaine.awesomelibraries.repository.callback.OnFetchReposCallback;

import java.util.List;

import javax.inject.Inject;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class RepositoryLocalImpl implements RetrofitRepository {

    public static final String TAG = "RetrofitRepositoryLocal";

    @Inject
    transient Realm mRealm;

    private RealmResults<Repo> mRepoRealmResults;

    public RepositoryLocalImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void save(final Repo repo) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(repo);
            }
        });
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
        // no implementation, just subscribe to this repository
    }

    @Override
    public void subscribe(OrderedRealmCollectionChangeListener<RealmResults<Repo>> orderedRealmCollectionChangeListener) {
        if (mRepoRealmResults == null) {
            mRepoRealmResults = mRealm.where(Repo.class).findAllAsync();
        }
        mRepoRealmResults.addChangeListener(orderedRealmCollectionChangeListener);
    }

    @Override
    public void unsubscribe() {
        if (mRepoRealmResults != null) {
            mRepoRealmResults.removeAllChangeListeners();
        }
    }
}
