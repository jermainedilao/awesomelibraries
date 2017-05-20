package com.jermaine.awesomelibraries.view.activity.additem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.f2prateek.dart.HensonNavigable;
import com.jermaine.awesomelibraries.R;
import com.jermaine.awesomelibraries.view.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

@HensonNavigable
public class AddRepoActivity extends BaseActivity implements AddRepoContract.AddRepoView {

    private AddRepoPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repo);
        ButterKnife.bind(this);

        mPresenter = new AddRepoPresenterImpl(getComponent());
        mPresenter.setView(this);
    }

    public void onAddClick(View view) {
        mPresenter.onAddClick();
    }

    @Override
    public String getId() {
        return mEditTexts.get(0).getText().toString();
    }

    @Override
    public String getName() {
        return mEditTexts.get(1).getText().toString();
    }

    @Override
    public String getDescription() {
        return mEditTexts.get(2).getText().toString();
    }

    @Override
    public void setIdError() {
        mEditTexts.get(0).setError(getString(R.string.required));
    }

    @Override
    public void setNameError() {
        mEditTexts.get(1).setError(getString(R.string.required));
    }

    @Override
    public void setDescriptionError() {
        mEditTexts.get(2).setError(getString(R.string.required));
    }

    @Override
    public void finishScreen() {
        finish();
    }

    @BindViews({R.id.id, R.id.name, R.id.description})
    List<EditText> mEditTexts;
}
