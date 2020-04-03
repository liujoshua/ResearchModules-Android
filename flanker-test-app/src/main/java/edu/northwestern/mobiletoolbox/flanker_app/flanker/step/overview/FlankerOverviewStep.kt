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

import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerStepBranchingRule
import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerStepGroup
import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerStepType
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.StepType
import org.sagebionetworks.research.domain.step.implementations.UIStepBase
import org.sagebionetworks.research.domain.step.ui.action.Action
import org.sagebionetworks.research.domain.step.ui.theme.ColorTheme
import org.sagebionetworks.research.domain.step.ui.theme.ImageTheme

class FlankerOverviewStep(
        val flanker: Flanker,
        @JvmField val stepName: String?,
        @JvmField val stepGroup: FlankerStepGroup?,
        @JvmField val delayToNextStep: Int?,
        @JvmField val branchingNavigationRules: ArrayList<FlankerStepBranchingRule>?,
        @JvmField val stepBackTo: String?,
        @JvmField val nextStepIdentifier: String?,
        identifier: String,
        asyncActions: Set<AsyncActionConfiguration>,
        actions: Map<String, Action>?,
        hiddenActions: Set<String>?,
        title: String?,
        text: String?,
        detail: String?,
        footnote: String?,
        colorTheme: ColorTheme?,
        imageTheme: ImageTheme?
): UIStepBase(
    identifier, asyncActions, actions, hiddenActions, title, text, detail, footnote, colorTheme, imageTheme
), FlankerStepType {

    override fun copyWithIdentifier(identifier: String): UIStepBase {
        return FlankerOverviewStep(
                flanker, stepName, stepGroup, delayToNextStep, branchingNavigationRules, stepBackTo, nextStepIdentifier, identifier, asyncActions, actions, hiddenActions, title, text, detail, footnote, colorTheme, imageTheme
        )
    }

    override fun getStepName(): String? {
        return stepName
    }

    override fun getStepGroup(): FlankerStepGroup? {
        return stepGroup
    }

    override fun getBranchingNavigationRules(): java.util.ArrayList<FlankerStepBranchingRule>? {
        return branchingNavigationRules
    }

    override fun getStepBackTo(): String? {
        return stepBackTo
    }

    override fun getType(): String {
        return FlankerOverviewStep.TYPE_KEY
    }

    override fun getNextStepIdentifier(): String? {
        return nextStepIdentifier
    }

    companion object {
        @JvmStatic
        val TYPE_KEY = StepType.OVERVIEW
    }
}

class Flanker(
    val interimStar: Array<String>?,
    val starDelay: Int?,
    val flankers: Array<String>?,
    val flankerDelay: Int?,
    val testStimuli: Array<String>?
)