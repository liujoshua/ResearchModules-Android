package edu.northwestern.mobiletoolbox.common.test_app.inject;


import android.app.Application;

import org.sagebionetworks.research.data.inject.DataModule;
import org.sagebionetworks.research.domain.inject.AsyncActionModule;
import org.sagebionetworks.research.domain.inject.InputFieldsModule;
import org.sagebionetworks.research.domain.inject.TaskModule;
import org.sagebionetworks.research.mobile_ui.inject.PerformTaskModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import edu.northwestern.mobiletoolbox.common.test_app.MtbCommonTestApplication;

// joliu TODO StepModule
@Component(modules = {PerformTaskModule.class, TaskModule.class, AndroidSupportInjectionModule.class,
        InputFieldsModule.class, AsyncActionModule.class, MtbCommonTestAppModule.class, DataModule.class})
public interface MtbCommonTestAppComponent extends AndroidInjector<MtbCommonTestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        MtbCommonTestAppComponent build();
    }
}
