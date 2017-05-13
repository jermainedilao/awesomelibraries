package com.jermaine.awesomelibraries.view.activity.repolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jermaine.awesomelibraries.R;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.view.activity.base.BaseActivity;
import com.jermaine.awesomelibraries.view.activity.viewitem.Henson;
import com.jermaine.awesomelibraries.view.adapter.repolist.RepoListAdapter;
import com.jermaine.awesomelibraries.view.adapter.repolist.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListActivity extends BaseActivity implements RepoListContract.RepoListView {

    private RepoListPresenterImpl mPresenter;
    private RepoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        ButterKnife.bind(this);

        mPresenter = new RepoListPresenterImpl(getComponent());

        initializeViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setView(this);
    }

    public void initializeViews() {
        mAdapter = new RepoListAdapter(new ArrayList<Repo>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        setOnItemClickListener();
    }

    private void setOnItemClickListener() {
        mAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mPresenter.onItemClick(mAdapter.get(position));
            }
        });
    }

    @Override
    public void showProgressDialog() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissProgressDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void provideRepos(List<Repo> repoList) {
        mAdapter.updateData(repoList);
    }

    @Override
    public void startViewItemScreen(String repoJson) {
        Intent intent = Henson.with(this)
                .gotoViewItemActivity()
                .mRepoJson(repoJson)
                .build();
        startActivity(intent);
    }

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
}