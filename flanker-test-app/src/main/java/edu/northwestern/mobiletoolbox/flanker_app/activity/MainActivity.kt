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

package edu.northwestern.mobiletoolbox.flanker_app.activity

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import edu.northwestern.mobiletoolbox.flanker_app.R.layout
import org.sagebionetworks.research.domain.repository.TaskRepository
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity
import org.sagebionetworks.research.presentation.model.TaskView
import java.util.UUID
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        launchTask("Flanker", UUID.randomUUID())
    }

    private fun launchTask(taskIdentifier: String,
            taskRunUUID: UUID?) {
        val taskInfoView = taskRepository.getTaskInfo(taskIdentifier).blockingGet()

        val task = taskRepository.getTask(taskIdentifier).blockingGet()

        //TODO: mapper
        val taskView = TaskView.builder().setIdentifier(taskInfoView.identifier).build()

        Log.d(TAG, "Task: $task")
        val intent = PerformTaskActivity.createIntent(applicationContext, taskView, taskRunUUID)
        this.startActivity(intent)
    }

    companion object {
        @JvmStatic
        val TAG = MainActivity::class.java.name

        init {
//            System.loadLibrary("FlankerKitCore")
//            JavaSwift.init()
//            val cacheDir = CertInstaller.install(FlankerApp.sharedApplication.applicationContext)
//            FlankerKitCore.bootstrap(cacheDir)
        }
    }
}
