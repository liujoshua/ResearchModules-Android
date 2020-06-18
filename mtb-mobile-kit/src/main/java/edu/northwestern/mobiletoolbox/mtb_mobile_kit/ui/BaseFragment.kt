/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.ui

import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.data.MtbChoice
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.timer.TimeManagerImpl
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

abstract class BaseFragment : Fragment(), ComponentAvailability, StepConfiguration, RecordChoice {


    private val viewModel: BaseViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(BaseViewModel::class.java)
    }

    fun createIdentifier(name: String, suffix: String) = name.plus(" ").plus(suffix)
    private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

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
                if(isVisible) {
                    enableUI(true)
                    viewModel.timeManager.startResponse()
                }
            }
        }

        scheduler.schedule(runnable, viewModel.timeManager.enableUIDelay, TimeUnit.MILLISECONDS)
    }

    override fun <V : Parcelable, S : Parcelable> recordChoice(
            choice: MtbChoice<V, S>, practice: Boolean) {
        viewModel.setEndStepDate()
        viewModel.choiceAggregation(choice, practice)
    }

    override fun <V : Parcelable, S : Parcelable> recordChoice(
            choices: ArrayList<MtbChoice<V, S>>,
            practice: Boolean) {
        viewModel.setEndStepDate()
        var i = 0
        choices.forEach { choice ->
            val stepResult = viewModel.choiceAggregation(choice, practice, i)
            stepResult?.let {
                viewModel.configureAndRecordStepResult(stepResult)
            }
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
    override fun <V : Parcelable, S : Parcelable> recordNoChoice(choice: MtbChoice<V, S>,
            practice: Boolean) {
        viewModel.setEndStepDate()
        viewModel.recordedStep?.let {
            val stepResult = viewModel.createStepResult(choice, practice, it.identifier)
            viewModel.configureAndRecordStepResult(stepResult)
        }
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