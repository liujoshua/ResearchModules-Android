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

package org.sagebionetworks.research.assessmentmodel.adapter

import com.google.common.collect.ImmutableSet
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.model.interfaces.StepView
import org.sagebionetworks.assessmentmodel.Step as AMStep
import org.sagebionetworks.research.domain.step.interfaces.Step as SRStep

class WrappedAssessmentStep(val amStep: AMStep) : SRStep {
    companion object {
        fun provideInternalStepViewFactory(): InternalStepViewFactory {

            return InternalStepViewFactory { step, _ ->
                WrappedAssessmentStepView(step as WrappedAssessmentStep)
            }
        }

        const val TYPE = "org.sagebionetworks.research.assessmentmodel.adapter.WrappedAssessmentStep"
    }

    override fun getIdentifier(): String {
        return amStep.identifier
    }

    override fun getType(): String {
        return TYPE
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        // TODO implement if needed
        return ImmutableSet.of()
    }

    override fun copyWithIdentifier(identifier: String): SRStep {
        TODO("Not yet implemented")
    }

    fun getAssessmentModelStep(): AMStep {
        return amStep
    }

    class WrappedAssessmentStepView(val assessmentStep: WrappedAssessmentStep) : StepView {
        override fun getIdentifier(): String {
            return assessmentStep.identifier
        }

        override fun getType(): String {
            return assessmentStep.type
        }
    }

}

