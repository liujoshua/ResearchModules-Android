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

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Choice
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimeManagerImpl
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimerImpl

abstract class BaseFragment : Fragment(), ComponentAvailability, StepConfiguration, RecordChoice {

    private val presentationTimer = TimerImpl()

    val viewModel: BaseViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(BaseViewModel::class.java)
    }

    fun createIdentifier(name: String, suffix: String) = name.plus(" ").plus(suffix)

    /***
     * enableUIDelay will be counted in Millis
     */
    var enableUIDelay: Long = 0

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponentsVisibility(false)
        enableUI(false)
        configureStep()
        //TODO initialize timer manager
        viewModel.initTimeManager(TimeManagerImpl())
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        startDelayForPresentation()
    }

    override fun startDelayForPresentation() {
        fragmentComponentsVisibility(true)
        //performAnimatedSequence it'a a call that animate
    }

    //Needs to be an event that will call this methodin the end of presentation
    override fun stepIsReady() {
        val runnable = Runnable {
            kotlin.run {
                enableUI(true)
                viewModel.timeManager.startResponse()
            }
        }

        Handler().postDelayed(runnable, enableUIDelay)
    }

    /**
     *
     */
    override fun <V : Parcelable, S : Parcelable> recordChoice(
            choice: Choice<V, S>, practice: Boolean) {
        viewModel.setEndStepDate()
        viewModel.choiceAggregation(choice, practice)
    }

    /**
     *
     */
    override fun <V : Parcelable, S : Parcelable> recordChoice(
            choices: ArrayList<Choice<V, S>>,
            practice: Boolean) {
        viewModel.setEndStepDate()
        var i = 0
        choices.forEach { choice ->
            val stepResult = viewModel.choiceAggregation(choice, practice, i)
            viewModel.configureAndRecordStepResult((stepResult))
            i++
        }
    }

    /**
     * Record no choice from the user
     * Expected responses should be of the same type as responses for the task
     * There may be different values for actively and passively skipping a step
     * This is for passively skipping, actively skipping should be a Choice
     * For an undefined choice (program error), response can be = "" or null
     */
    override fun <V : Parcelable, S : Parcelable> recordNoChoice(choice: Choice<V, S>,
            practice: Boolean) {
        viewModel.setEndStepDate()
        val stepResult = viewModel.createStepResult(choice, practice, viewModel.recordedStep.identifier)
        viewModel.configureAndRecordStepResult(stepResult)
    }

    override fun onPause() {
        super.onPause()
        //here is method for pause any instruction that should be done when you pause
    }

    override fun onDestroy() {
        super.onDestroy()
        //maybe create class that will force clean data
    }
}
