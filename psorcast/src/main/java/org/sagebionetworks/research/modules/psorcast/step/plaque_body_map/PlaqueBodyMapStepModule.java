/*
 * BSD 3-Clause License
 *
 * Copyright 2019  Sage Bionetworks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1.  Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2.  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3.  Neither the name of the copyright holder(s) nor the names of any contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission. No license is granted to the trademarks of
 * the copyright holders even if such marks are included in this software.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sagebionetworks.research.modules.psorcast.step.plaque_body_map;

import static org.sagebionetworks.research.domain.inject.GsonModule.createPassThroughDeserializer;

import com.google.gson.JsonDeserializer;

import org.sagebionetworks.research.domain.inject.GsonModule;
import org.sagebionetworks.research.domain.inject.GsonModule.ClassKey;
import org.sagebionetworks.research.domain.inject.StepModule.StepClassKey;
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule;
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory;
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.StepViewKey;
import org.sagebionetworks.research.presentation.inject.ShowStepViewModelModule.StepViewClassKey;
import org.sagebionetworks.research.presentation.inject.StepViewModule;
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory;
import org.sagebionetworks.research.presentation.inject.StepViewModule.StepTypeKey;
import org.sagebionetworks.research.presentation.mapper.DrawableMapper;
import org.sagebionetworks.research.presentation.model.interfaces.StepView;
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowStepViewModelFactory;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Dagger configuration to register PlaqueBodyMapStep as a new type of Step with associated type-key of
 * "plaqueBodyMapStep".
 * <p>
 * The creation flow of a Step is: JSON to a Step class, Step class to a StepView class, and StepView class is
 * displayed by a Fragment. This Module defines the bindings for the factories which instantiate the classes
 * associated with "plaqueBodyMapStep".
 */
@Module
public class PlaqueBodyMapStepModule {

    /**
     * Registers PlaqueBodyMapStep as the POJO associated with "plaqueBodyMapStep". This configures Gson's polymorphic
     * deserialization of the Step class hierarchy.
     *
     * @see org.sagebionetworks.research.domain.inject.StepModule#provideType(Map)
     */
    @Provides
    @IntoMap
    @StepClassKey(PlaqueBodyMapStep.class)
    static String providePlaqueBodyMapStepTypeKey() {
        return PlaqueBodyMapStep.TYPE;
    }


    /**
     * Register deserializer to read a PlaqueBodyMapStep from a JSON-format Task's "steps" field.
     *
     * @return PlaqueBodyMapStep's Gson deserializer
     * @see GsonModule#provideJsonDeserializerMap()
     */
    @Provides
    @IntoMap
    @ClassKey(PlaqueBodyMapStep.class)
    static JsonDeserializer<?> providePlaqueBodyMapStepDeserializer() {
        return createPassThroughDeserializer(PlaqueBodyMapStep.class);
    }

    /**
     * Register the factory method for mapping a Step class to a StepView class for a "plaqueBodyMapStep".
     *
     * @see StepViewModule#stepToFactoryMap()
     * @see StepViewModule#provideStepViewFactory(Map, DrawableMapper)
     */
    @Provides
    @IntoMap
    @StepTypeKey(PlaqueBodyMapStep.TYPE)
    static InternalStepViewFactory providePlaqueBodyMapStepViewFactory() {
        return PlaqueBodyMapStepView::fromPlaqueBodyMapStep;
    }

    /**
     * Registers a Fragment to be shown when navigating to a "plaqueBodyMapStep".
     *
     * @return instance of ShowPlaqueBodyMapStepFragment
     * @see ShowStepModule#fragmentFactoryMap()
     * @see ShowStepModule#provideShowStepFragmentFactory(Map)
     */
    @Provides
    @IntoMap
    @StepViewKey(PlaqueBodyMapStepView.TYPE)
    static ShowStepFragmentFactory provideShowPlaqueBodyStepFragment() {
        return ShowPlaqueBodyMapStepFragment::newInstance;
    }

    /**
     * ShowPlaqueBodyMapStepFragment extends ShowStepFragmentBase, which uses an abstract factory to instantiate a
     * ViewModel for its descendants.
     * <p>
     * Note that the ShowPlaqueBodyMapStepFragment itself may need to be configured if it uses Dagger.
     *
     * @return instance of ShowStepViewModelFactory for creation of ViewModels for a "plaqueBodyMapStep"
     * @see org.sagebionetworks.research.presentation.inject.ShowStepViewModelModule#provideShowStepViewModelFactory(Map)
     * @see PsorcastShowStepFragmentsModule#contributeShowPlaqueBodyStepFragmentInjector()
     */
    @Provides
    @IntoMap
    @StepViewClassKey(PlaqueBodyMapStepView.TYPE)
    static ShowStepViewModelFactory<?, ? extends StepView> providePlaqueBodyMapStepVMF() {
        return new ShowPlaqueBodyStepViewModelFactory();
    }
}
