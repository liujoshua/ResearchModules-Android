package edu.northwestern.mobiletoolbox.common.test_app.inject;

import android.app.Application;
import android.content.Context;

import org.sagebionetworks.research.mobile_ui.inject.ShowStepFragmentModule;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment;
import org.sagebionetworks.research.presentation.perform_task.TaskResultProcessingManager.TaskResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoSet;
import edu.northwestern.mobiletoolbox.common.test_app.MainActivity;
import io.reactivex.Single;

@Module
public abstract class MtbCommonTestAppModule {
    private static final Logger LOG = LoggerFactory.getLogger(MtbCommonTestAppModule.class);

    @Provides
    static Context getApplicationContext(Application application) {
        return application.getApplicationContext();
    }

    // joliu TODO add common step module
    @ContributesAndroidInjector(modules = {ShowStepFragmentModule.class})
    abstract PerformTaskFragment contributesPerformTaskFragmentInjector();

    @ContributesAndroidInjector
    abstract PerformTaskActivity contributePerformTaskActivityInjector();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();

    @Provides
    @IntoSet
    static TaskResultProcessor getTaskResultProcessor() {
        return taskResult -> Single.just(taskResult)
                .doOnSuccess(t -> LOG.info("Final TaskResult: {}", taskResult.toString()))
                .ignoreElement();
    }
}
