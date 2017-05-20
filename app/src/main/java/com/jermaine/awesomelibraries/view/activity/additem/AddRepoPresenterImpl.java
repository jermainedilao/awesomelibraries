package com.jermaine.awesomelibraries.view.activity.additem;


import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.base.BaseView;
import com.jermaine.awesomelibraries.di.component.AppComponent;
import com.jermaine.awesomelibraries.repository.local.RepositoryLocalImpl;

import javax.inject.Inject;

public class AddRepoPresenterImpl implements AddRepoContract.AddRepoPresenter {

    @Inject
    transient RepositoryLocalImpl mRepository;

    private AddRepoContract.AddRepoView mView;

    public AddRepoPresenterImpl(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(BaseView view) {
        mView = (AddRepoContract.AddRepoView) view;
    }

    @Override
    public void onAddClick() {
        String id = mView.getId();
        String name = mView.getName();
        String description = mView.getDescription();

        boolean error = false;

        if (id.isEmpty()) {
            error = true;
            mView.setIdError();
        }

        if (name.isEmpty()) {
            error = true;
            mView.setNameError();
        }

        if (description.isEmpty()) {
            error = true;
            mView.setDescriptionError();
        }

        if (error) return;

        mRepository.save(new Repo(id, name, description));
        mView.finishScreen();
    }
}
