package edu.northwestern.mobiletoolbox.flanker.test_app.inject;


import android.app.Application;

import org.sagebionetworks.research.assessmentmodel.adapter.inject.AssessmentModelDataModule;
import org.sagebionetworks.research.domain.inject.AsyncActionModule;
import org.sagebionetworks.research.domain.inject.InputFieldsModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import edu.northwestern.mobiletoolbox.flanker.test_app.FlankerTestApplication;

@Component(modules = {FlankerPerformTaskModule.class, AndroidSupportInjectionModule.class,
        InputFieldsModule.class, AsyncActionModule.class, FlankerTestAppModule.class,
        FlankerStepConfigurationModule.class, FlankerTaskConfigurationModule.class, AssessmentModelDataModule.class})
public interface FlankerTestAppComponent extends AndroidInjector<FlankerTestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        FlankerTestAppComponent build();
    }
}
