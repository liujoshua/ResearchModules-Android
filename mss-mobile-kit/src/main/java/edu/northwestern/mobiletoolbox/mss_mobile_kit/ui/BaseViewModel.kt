/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Choice
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Step
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.StepResult
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimeManager
import org.sagebionetworks.research.domain.result.AnswerResultType
import org.sagebionetworks.research.domain.result.interfaces.Result
import java.util.Date

class BaseViewModel() : ViewModel() {

    //TODO probably other type
    var startStepDate: Date? = null
    private var endStepDate: Date? = null

    fun setEndStepDate() {
        endStepDate = Date()
    }

    lateinit var timeManager: TimeManager

    fun initTimeManager(timeManager: TimeManager) {
        this.timeManager = timeManager
    }

    //TODO initialize
    var recordedStep: Step = Step("", "")

    fun <V : Parcelable, S : Parcelable> choiceAggregation(
            choice: Choice<V, S>, practice: Boolean, index: Int? = null): StepResult<V, S> {
        val identifier = index?.let {
            recordedStep.identifier.plus(" ").plus(index)
        } ?: recordedStep.identifier
        return createStepResult(choice, practice, identifier)
    }

    fun <V : Parcelable, S : Parcelable> createStepResult(choice: Choice<V, S>, practice: Boolean,
            identifier: String): StepResult<V, S> {
        //TODO create StepResult
        return StepResult(response = choice.value, score = choice.score, identifier = identifier, practice = practice)
    }

    fun <R : Parcelable, S : Parcelable> configureAndRecordStepResult(stepResult: StepResult<R, S>) {
        val configuredStepResult = configureStepResult(stepResult)
        recordStepResult(configuredStepResult)
    }

    fun <R : Parcelable, S : Parcelable> configureStepResult(stepResult: StepResult<R, S>): StepResult<R, S> {
        stepResult.startDate = startStepDate
        stepResult.endDate = endStepDate
        stepResult.responseTime = timeManager.getResponseTime()
        return stepResult
    }

    fun <R : Parcelable, S : Parcelable> recordStepResult(stepResult: StepResult<R, S>) {
        //TODO find impl from sage AnswerResult()?!!! answerType?? type??
        val result = edu.northwestern.mobiletoolbox.mss_mobile_kit.data.AnswerResult<AnswerResultType>(
                _identifier = stepResult.identifier,
                value = stepResult,
                answerType = null,
                type = null)

        addResult(result)
    }

    fun addResult(result: Result) {
     //TODO need to understand how sage stores this data
     // IOS: self.stepViewModel.taskResult.appendStepHistory(with: stepResult)
    }
}

