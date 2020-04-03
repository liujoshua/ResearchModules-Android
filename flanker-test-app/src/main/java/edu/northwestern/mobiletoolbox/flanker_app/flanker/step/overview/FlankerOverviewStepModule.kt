/*
 * BSD 3-Clause License
 *
 * Copyright 2020  Sage Bionetworks. All rights reserved.
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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step.overview

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.sagebionetworks.research.domain.inject.StepModule.StepClassKey
import org.sagebionetworks.research.domain.step.StepType
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule
import org.sagebionetworks.research.modules.common.CommonStepModule
import org.sagebionetworks.research.presentation.inject.ShowStepViewModelModule.StepViewClassKey
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.inject.StepViewModule.StepTypeKey
import org.sagebionetworks.research.presentation.mapper.DrawableMapper
import org.sagebionetworks.research.presentation.model.interfaces.StepView
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowStepViewModelFactory
import org.sagebionetworks.research.presentation.show_step.show_step_view_model_factories.ShowUIStepViewModelFactory

//@Module(includes = [CommonStepModule::class])
@Module
class FlankerOverviewStepModule {
    @Provides
    @IntoMap
    @StepClassKey(FlankerOverviewStep::class)
    fun provideFlankerOverviewStepClassInfo() = FlankerOverviewStep.TYPE_KEY

    @Provides
    @IntoMap
    @StepViewClassKey(FlankerOverviewStepView.TYPE)
    fun provideFlankerOverviewStepVMF(): ShowStepViewModelFactory<*, out StepView>  = ShowUIStepViewModelFactory<FlankerOverviewStepView>()

    @Provides
    @IntoMap
    @StepTypeKey(StepType.OVERVIEW)
    fun provideFlankerOverviewStepViewFactory(): InternalStepViewFactory {
        return InternalStepViewFactory { step: Step, mapper: DrawableMapper ->
            FlankerOverviewStepView.fromFlankerOverviewStep(step, mapper)
        }
    }

    @Provides
    @IntoMap
    @ShowStepModule.StepViewKey(FlankerOverviewStepView.TYPE)
    fun provideShowFlankerOverviewStepFragmentFactory(): ShowStepModule.ShowStepFragmentFactory {
        return ShowStepModule.ShowStepFragmentFactory { stepView ->
            FlankerOverviewStepFragment.newInstance(stepView)
        }
    }
}