package com.jermaine.awesomelibraries.view.activity.repolist;


import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.base.BasePresenter;
import com.jermaine.awesomelibraries.base.BaseView;

import java.util.List;

public interface RepoListContract {
    interface RepoListPresenter extends BasePresenter {
        void fetchLocal();

        void fetchServer();

        void onItemClick(Repo repo);
    }

    interface RepoListView extends BaseView {
        void showProgressDialog();

        void dismissProgressDialog();

        void provideRepos(List<Repo> repoList);

        void startViewItemScreen(String repoJson);
    }
}
