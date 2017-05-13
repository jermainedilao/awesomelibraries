package com.jermaine.awesomelibraries.view.activity.base;


import android.support.v7.app.AppCompatActivity;

import com.jermaine.awesomelibraries.app.App;
import com.jermaine.awesomelibraries.di.component.AppComponent;

public class BaseActivity extends AppCompatActivity {
    public AppComponent getComponent() {
        return ((App) getApplication()).getComponent();
    }
}
