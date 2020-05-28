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

package edu.northwestern.mobiletoolbox.flanker.test_app

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.view.ContextThemeWrapper
import dagger.android.support.DaggerAppCompatActivity
import edu.northwestern.mobiletoolbox.flanker.test_app.databinding.ActivityContainerBinding
import edu.northwestern.mobiletoolbox.flanker.test_app.databinding.AssessmentRowBinding
import org.sagebionetworks.research.assessmentmodel.adapter.AssessmentModelTaskFactory.AssessmentConfig
import org.sagebionetworks.research.domain.repository.TaskRepository
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity
import org.sagebionetworks.research.presentation.model.TaskView
import javax.inject.Inject

class ContainerActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var assessmentConfigs: Map<String, AssessmentConfig>

    @Inject
    lateinit var taskRepository: TaskRepository

    companion object {
        const val REQUEST_CODE_ASSESSMENT = 1;
    }

    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for (assessmentConfig in assessmentConfigs.entries) {
            with(assessmentConfig.value)
            {
                addAssessment(title, assessmentConfig.key, resourceName, packageName, theme)
            }
        }


        addAssessment("Flanker - Blue", "flanker_inhibitory_control", "flanker_inhibitory_control",
                "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
        addAssessment("MFS", "mfs_pilot_1a", "mfs_pilot_1a", "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
        addAssessment("Number Match", "number_match", "number_match", "edu.northwestern.mobiletoolbox",
                R.style.BlueberryTheme)
        addAssessment("DCCS", "dimensional_change_card_sort", "dimensional_change_card_sort",
                "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
    }

    private fun addAssessment(title: String, assessmentId: String, resourceName: String, packageName: String,
            theme: Int = -1) {
        val inflater = if (theme < 0) layoutInflater else LayoutInflater.from(ContextThemeWrapper(this, theme))
        val row = AssessmentRowBinding.inflate(inflater, binding.crfTaskContainer, false)
        row.taskName.text = title
        row.buttonStartTask.setOnClickListener {
            val taskInfoView = taskRepository.getTaskInfo(assessmentId).blockingGet()
            val taskView = TaskView.builder().setIdentifier(taskInfoView.identifier).build()

            val intent = PerformTaskActivity.createIntent(applicationContext, taskView, null)
            this.startActivity(intent)

        }
        binding.crfTaskContainer.addView(row.root)
    }
}
