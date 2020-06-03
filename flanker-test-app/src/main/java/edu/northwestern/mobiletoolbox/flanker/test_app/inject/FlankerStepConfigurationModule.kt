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
package edu.northwestern.mobiletoolbox.flanker.test_app.inject

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import edu.northwestern.mobiletoolbox.flanker.inject.FlankerFormStepModule
import edu.northwestern.mobiletoolbox.flanker.inject.FlankerInstructionFormStepModule
import edu.northwestern.mobiletoolbox.flanker.inject.FlankerInstructionStepModule
import edu.northwestern.mobiletoolbox.flanker.inject.FlankerOverviewStepModule
import org.sagebionetworks.research.assessmentmodel.adapter.WrappedAssessmentStep
import org.sagebionetworks.research.assessmentmodel.adapter.WrappedAssessmentStep.WrappedAssessmentStepView
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.StepViewKey
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowUIStepFragment
import org.sagebionetworks.research.modules.common.step.instruction.InstructionStep
import org.sagebionetworks.research.modules.common.step.instruction.ShowInstructionStepFragment

/**
 * Provides
 */
@Module(
        includes = [FlankerInstructionFormStepModule::class, FlankerInstructionStepModule::class, FlankerOverviewStepModule::class, FlankerFormStepModule::class])
class FlankerStepConfigurationModule {

    @Provides
    @IntoMap
    @StepViewKey(WrappedAssessmentStep.TYPE)
    fun provideWrappedAssessmentStepFragmentFactory(): ShowStepFragmentFactory {
        return ShowStepModule.ShowStepFragmentFactory {
            when ((it as WrappedAssessmentStepView).getAssessmentModelStep()) {
                is InstructionStep -> ShowInstructionStepFragment()
                else -> ShowUIStepFragment()
            }
        }
    }
}
