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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.northwestern.mobiletoolbox.flanker_app.R
import edu.northwestern.mobiletoolbox.flanker_app.flanker.model.FlankerType
import edu.northwestern.mobiletoolbox.flanker_app.flanker.model.FlankerType.INTERIM_STAR
import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.form.FlankerFormStepView
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.flankerLeftTextView1
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.flankerLeftTextView2
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.flankerRightTextView1
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.flankerRightTextView2
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.flankerTargetTextView
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.leftResponseButton
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.progressBar
import kotlinx.android.synthetic.main.fragment_show_flanker_form_step.rightResponseButton
import org.sagebionetworks.research.domain.task.navigation.TaskProgress
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowStepFragmentBase
import org.sagebionetworks.research.presentation.model.interfaces.StepView
import org.sagebionetworks.research.presentation.perform_task.PerformTaskViewModel

class ShowFlankerFormStepFragment : Fragment() {

    lateinit var stepView: FlankerFormStepView
    lateinit var taskViewModel: PerformTaskViewModel

    val flankers: Array<TextView> by lazy {
        arrayOf(
            flankerLeftTextView1,
            flankerLeftTextView2,
            flankerTargetTextView,
            flankerRightTextView2,
            flankerRightTextView1
        )
    }

    private var flankerTypeToDisplay: FlankerType = INTERIM_STAR

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_flanker_form_step, container, false)

        this.stepView = arguments!!.getSerializable("STEP_VIEW") as FlankerFormStepView

        val performTaskFragment = (parentFragment as PerformTaskFragment)
        this.taskViewModel = ViewModelProviders.of(performTaskFragment)
                .get<PerformTaskViewModel>(PerformTaskViewModel::class.java)

//        view.button.setOnClickListener { v -> performTaskFragment.goForward() }
//
//        view.text.text = stepView.toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind progress
        taskViewModel.taskProgress
            .observe(this, Observer<TaskProgress?> {
                progressBar.progress = it?.progress ?: 0
            })

        arrayOf(leftResponseButton, rightResponseButton).forEach {
            it.setOnTouchListener { button, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> processTouchDown(button as FlankerResponseButton)
                    MotionEvent.ACTION_UP -> processTouchUp(button as FlankerResponseButton)
                    else -> Log.d(TAG, "$button received other action ${event.action}")
                }

                true
            }
        }
    }

    private fun configureStep() {
        startFlankerAnimation()
    }

    private fun processTouchDown(button: FlankerResponseButton) {
        Log.d(TAG, "Button response OID: ${button.itemResponseOID}")
    }

    private fun processTouchUp(button: FlankerResponseButton) {
        Log.d(TAG, "Button response OID: ${button.itemResponseOID}")
    }

    private fun startFlankerAnimation() {
        // reset flankers
        flankers.forEach { it.text = "" }
        leftResponseButton.itemResponseOID = null
        rightResponseButton.itemResponseOID = null
    }

    companion object {
        @JvmStatic
        val TAG = this::class.java.name

        @JvmStatic
        fun newInstance(stepView: StepView) =
                ShowFlankerFormStepFragment().apply {
                    arguments = ShowStepFragmentBase.createArguments(stepView)
                }
    }
}
