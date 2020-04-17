package edu.northwestern.mobiletoolbox.common.test_app;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import edu.northwestern.mobiletoolbox.common.test_app.inject.DaggerMtbCommonTestAppComponent;

public class MtbCommonTestApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        AndroidThreeTen.init(this);
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMtbCommonTestAppComponent
                .builder()
                .application(this)
                .build();
    }
}
