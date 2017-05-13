package com.jermaine.awesomelibraries.view.activity.viewitem;


import com.google.gson.Gson;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.base.BaseView;
import com.jermaine.awesomelibraries.di.component.AppComponent;

import javax.inject.Inject;

public class ViewItemPresenterImpl implements ViewItemContract.ViewItemPresenter {

    @Inject
    transient Gson mGson;

    private ViewItemContract.ViewItemView mView;

    public ViewItemPresenterImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(BaseView view) {
        mView = (ViewItemContract.ViewItemView) view;

        Repo repo = mGson.fromJson(mView.getRepoJson(), Repo.class);

        mView.setId(repo.getId());
        mView.setName(repo.getName());
        mView.setDescription(repo.getDescription());
    }
}
