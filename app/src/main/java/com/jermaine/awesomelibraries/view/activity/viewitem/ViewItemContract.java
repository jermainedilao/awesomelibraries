package com.jermaine.awesomelibraries.view.activity.viewitem;


import com.jermaine.awesomelibraries.base.BasePresenter;
import com.jermaine.awesomelibraries.base.BaseView;

public interface ViewItemContract {
    interface ViewItemPresenter extends BasePresenter {

    }

    interface ViewItemView extends BaseView {
        String getRepoJson();

        void setId(String id);

        void setName(String name);

        void setDescription(String description);
    }
}
