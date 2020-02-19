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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step

import com.google.common.collect.ImmutableMap
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.presentation.DisplayString
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.mapper.DrawableMapper
import org.sagebionetworks.research.presentation.model.ColorThemeView
import org.sagebionetworks.research.presentation.model.ImageThemeView
import org.sagebionetworks.research.presentation.model.action.ActionView
import org.sagebionetworks.research.presentation.model.implementations.UIStepViewBase
import org.sagebionetworks.research.presentation.model.interfaces.StepView

class FormStepView(
        identifier: String,
        actions: ImmutableMap<String, ActionView>,
        title: DisplayString?,
        text: DisplayString?,
        detail: DisplayString?,
        footnote: DisplayString?,
        colorTheme: ColorThemeView?,
        imageTheme: ImageThemeView?
): UIStepViewBase(
        identifier, actions, title, text, detail, footnote, colorTheme, imageTheme
) {

    companion object {
        const val TYPE = "form"

        val fromFormStep = object: InternalStepViewFactory {
            override fun apply(step: Step?, mapper: DrawableMapper?): StepView? {
                if (step !is FormStep) {
                    throw IllegalArgumentException("Provided step: $step is not a FormStep")
                }

                val activeStep = fromUIStep(step, mapper)
                return FormStepView(
                        activeStep.identifier,
                        activeStep.actions,
                        activeStep.title,
                        activeStep.text,
                        activeStep.detail,
                        activeStep.footnote,
                        activeStep.colorTheme,
                        activeStep.imageTheme)
            }
        }
        
//        @JvmStatic
//        fun <S: FormStep> fromFormStep(step: S, mapper: DrawableMapper): FormStepView {
//            val activeStep = fromUIStep(step, mapper)
//            return FormStepView(
//                    activeStep.identifier,
//                    activeStep.actions,
//                    activeStep.title,
//                    activeStep.text,
//                    activeStep.detail,
//                    activeStep.footnote,
//                    activeStep.colorTheme,
//                    activeStep.imageTheme)
//        }
    }
}