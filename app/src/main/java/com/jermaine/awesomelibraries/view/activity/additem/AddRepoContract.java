package com.jermaine.awesomelibraries.view.activity.additem;


import com.jermaine.awesomelibraries.base.BasePresenter;
import com.jermaine.awesomelibraries.base.BaseView;

public interface AddRepoContract {
    interface AddRepoPresenter extends BasePresenter {
        void onAddClick();
    }

    interface AddRepoView extends BaseView {
        String getId();

        String getName();

        String getDescription();

        void setIdError();

        void setNameError();

        void setDescriptionError();

        void finishScreen();
    }
}
