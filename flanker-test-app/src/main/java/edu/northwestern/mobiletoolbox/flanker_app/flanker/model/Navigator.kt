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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.model

import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerEngine
import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerStep
import edu.northwestern.mobiletoolbox.flanker_app.jni.FlankerStepType
import org.sagebionetworks.research.domain.result.interfaces.TaskResult
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.domain.task.Task
import org.sagebionetworks.research.domain.task.navigation.StepAndNavDirection
import org.sagebionetworks.research.domain.task.navigation.StepNavigator
import org.sagebionetworks.research.domain.task.navigation.StepNavigatorFactory
import org.sagebionetworks.research.domain.task.navigation.TaskProgress

class Navigator(val task: Task): StepNavigator {

    private val steps: ArrayList<Step> = ArrayList(task.steps)


    private val engine = FlankerEngine.init(ArrayList(steps.mapNotNull { FlankerStep.fromStepType(it as FlankerStepType?)}), null)

    class Factory: StepNavigatorFactory {
        override fun create(task: Task, progressMarkers: List<String>?): StepNavigator {
            return Navigator(task)
        }
    }

    override fun getNextStep(step: Step?, taskResult: TaskResult): StepAndNavDirection {
        val currentFlankerStep = FlankerStep.fromStepType(step as FlankerStepType?)
        engine.getNextStep(currentFlankerStep)?.let { nextStep ->
            return StepAndNavDirection(steps.find { s -> nextStep.identifier == s.identifier })
        } ?: run {
            return StepAndNavDirection(null)
        }
    }

    override fun getPreviousStep(step: Step, taskResult: TaskResult): Step? {
        val currentFlankerStep = FlankerStep.fromStepType(step as FlankerStepType)
        return engine.getPreviousStep(currentFlankerStep)?.let {
            steps.find { s -> s.identifier == it.identifier }
        }
    }

    override fun getProgress(step: Step, taskResult: TaskResult): TaskProgress? {
        var currentStepIndex = steps.indexOfFirst { step.identifier == it.identifier }

        if (currentStepIndex == -1) currentStepIndex = 0

        return TaskProgress(currentStepIndex, steps.count(), false)
    }

    override fun getStep(identifier: String): Step? {
        return steps.first { it.identifier == identifier }
    }

    override fun getSteps(): List<Step> {
        return task.steps
    }
}