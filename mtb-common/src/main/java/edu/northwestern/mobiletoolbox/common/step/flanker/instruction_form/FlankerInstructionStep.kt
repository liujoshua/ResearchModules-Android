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

package edu.northwestern.mobiletoolbox.common.step.flanker.instruction_form

import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import com.google.gson.Gson
import edu.northwestern.mobiletoolbox.common.step.flanker.form.InputField
import edu.northwestern.mobiletoolbox.common.step.flanker.instruction_form.FlankerInstructionStep.Companion.TYPE
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.domain.step.interfaces.UIStep
import org.sagebionetworks.research.domain.step.ui.action.Action
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.presentation.DisplayString
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.mapper.DrawableMapper
import org.sagebionetworks.research.presentation.model.ColorThemeView
import org.sagebionetworks.research.presentation.model.ImageThemeView
import org.sagebionetworks.research.presentation.model.action.ActionView
import org.sagebionetworks.research.presentation.model.interfaces.StepView
import org.sagebionetworks.research.presentation.model.interfaces.UIStepView

data class FlankerInstructionStep(
        private val identifier: String,
        val flankerImageNames: List<String>,
        val inputFields: List<InputField>,
        val isInstruction: Boolean,
        private val text: String
) : UIStep {

    companion object {
        const val TYPE = "flankerInstructionForm"

        @JvmStatic
        fun provideInternalStepViewFactory(): InternalStepViewFactory {
            return InternalStepViewFactory { step: Step, mapper: DrawableMapper? ->
                FlankerInstructionStepView.create(step as FlankerInstructionStep)
            }
        }

        @JvmStatic
        fun provideStepFragmentFactory(): ShowStepFragmentFactory {
            return ShowStepFragmentFactory { stepView: StepView ->
                ShowFlankerInstructionStepFragment.newInstance(stepView)
            }
        }
    }

    override fun getType(): kotlin.String {
        return TYPE
    }

    override fun getIdentifier(): kotlin.String {
        return identifier
    }

    override fun getText(): kotlin.String? {
        return text
    }

    override fun getFootnote(): String? {
        return null
    }

    override fun getTitle(): String? {
        return null
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return ImmutableSet.of()
    }

    override fun getActions(): ImmutableMap<String, Action> {
        return ImmutableMap.of()
    }

    override fun getDetail(): String? {
        return null
    }

    override fun copyWithIdentifier(identifier: String): Step {
        return FlankerInstructionStep(
                identifier, flankerImageNames, inputFields, isInstruction, text)
    }

    override fun getHiddenActions(): ImmutableSet<String> {
        return ImmutableSet.of()
    }
}

data class FlankerInstructionStepView(
        private val identifier: String,
        val flankerImageNames: List<String>,
        val inputFields: List<InputField>,
        val isInstruction: Boolean,
        private val text: String?
) : UIStepView {

    companion object {
        fun create(flankerInstructionStep: FlankerInstructionStep): FlankerInstructionStepView {
            with(flankerInstructionStep) {
                return FlankerInstructionStepView(
                        identifier, flankerImageNames, inputFields, isInstruction, text)
            }
        }
    }

    override fun getIdentifier(): String {
        return identifier
    }

    override fun getColorTheme(): ColorThemeView? {
        return null
    }

    override fun getType(): String {
        return TYPE
    }

    override fun getText(): DisplayString? {
        return null
    }

    override fun getFootnote(): DisplayString? {
        return null
    }

    override fun getActionFor(actionType: String?): ActionView? {
        return null
    }

    override fun getImageTheme(): ImageThemeView? {
        return null
    }

    override fun getTitle(): DisplayString? {
        return null
    }

    override fun getActions(): ImmutableMap<String, ActionView> {
        return ImmutableMap.of()
    }

    override fun getDetail(): DisplayString? {
        return null
    }
}