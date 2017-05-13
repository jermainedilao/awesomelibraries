package com.jermaine.awesomelibraries.view.activity.viewitem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.jermaine.awesomelibraries.R;
import com.jermaine.awesomelibraries.view.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class ViewItemActivity extends BaseActivity implements ViewItemContract.ViewItemView {

    @InjectExtra
    String mRepoJson;

    private ViewItemPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        ButterKnife.bind(this);
        Dart.inject(this);

        mPresenter = new ViewItemPresenterImpl(getComponent());
        mPresenter.setView(this);
    }

    @Override
    public String getRepoJson() {
        return mRepoJson;
    }

    @Override
    public void setId(String id) {
        mTextViews.get(0).setText(id);
    }

    @Override
    public void setName(String name) {
        mTextViews.get(1).setText(name);
    }

    @Override
    public void setDescription(String description) {
        mTextViews.get(2).setText(description);
    }

    @BindViews({R.id.id, R.id.name, R.id.description})
    List<TextView> mTextViews;
}
