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

package org.sagebionetworks.research.assessmentmodel.adapter

import android.content.Context
import android.content.res.Resources.NotFoundException
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import edu.northwestern.mobiletoolbox.serialization.mfs.MFSAssessmentObject
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import kotlinx.serialization.json.Json
import org.sagebionetworks.assessmentmodel.AssessmentProvider
import org.sagebionetworks.assessmentmodel.Node
import org.sagebionetworks.assessmentmodel.serialization.AssessmentGroupInfoObject
import org.sagebionetworks.assessmentmodel.serialization.FileAssessmentProvider
import org.sagebionetworks.assessmentmodel.serialization.FileLoaderAndroid
import org.sagebionetworks.assessmentmodel.serialization.TransformableAssessmentObject
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.repository.TaskRepository
import org.sagebionetworks.research.domain.result.interfaces.TaskResult
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.domain.step.ui.action.Action
import org.sagebionetworks.research.domain.task.Task
import org.sagebionetworks.research.domain.task.TaskInfoView
import org.threeten.bp.Duration
import java.util.UUID
import  org.sagebionetworks.assessmentmodel.Step as SRStep

class AssessmentModelTaskFactory(val context: Context, val json: Json,
        val assessmentConfigs: Map<String, AssessmentConfig>) :
        TaskRepository {

    data class AssessmentConfig(val title: String, val resourceName: String, val packageName: String,
            val theme: Int = -1)

    // TODO persist across app runs
    protected val taskResultMap: MutableMap<UUID, TaskResult> = mutableMapOf()

    override fun getTaskResult(taskRunUUID: UUID?): Maybe<TaskResult> {
        val taskResult = taskResultMap[taskRunUUID!!] ?: return Maybe.empty()
        return Maybe.just(taskResult)
    }

    override fun resolveDrawableFromString(name: String): Int {
        val resId: Int = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId == 0) {
            // The resource was not found
            throw NotFoundException(
                    "Resource $name couldn't be resolved as a drawable.")
        } else {
            resId
        }
    }

    fun getAssessmentProvider(assessmentId: String): AssessmentProvider {
        val fileLoader = FileLoaderAndroid(context.resources, context.packageName)
        val assessmentConfig = assessmentConfigs[assessmentId]
                ?: throw IllegalStateException("No AssessmentConfig found for assessmentId: $assessmentId")

        val assessmentGroup = AssessmentGroupInfoObject(
                assessments = listOf(
                        TransformableAssessmentObject(assessmentId, assessmentConfig.resourceName)),
                packageName = assessmentConfig.packageName)
        return FileAssessmentProvider(fileLoader, assessmentGroup, json)
    }

    override fun getTaskInfo(taskIdentifier: String): Single<TaskInfoView> {

        val assessment = getAssessmentProvider(taskIdentifier).loadAssessment(taskIdentifier)

        val taskInfoView = object : TaskInfoView {
            override fun getIdentifier(): String {
                return taskIdentifier!!
            }

            override fun getEstimatedDuration(): Duration? {
                return if (assessment != null) Duration.ofMinutes(assessment.estimatedMinutes.toLong()) else null
            }

            override fun getSubtitle(): String? {
                return null
            }

            override fun getCopyright(): String? {
                return null
            }

            override fun getTitle(): String? {
                return null
            }

            override fun getDetail(): String? {
                return null
            }
        }
        return Single.just(taskInfoView)
    }

    override fun getTask(taskIdentifier: String): Single<Task> {
        val assessment = getAssessmentProvider(taskIdentifier).loadAssessment(taskIdentifier)
        if (assessment is MFSAssessmentObject) {
            return Single.just(map(assessment))
        }
        return Single.error(IllegalStateException("Expecting Assessment to deserialize to an MFSAssessmentObject"))
    }

    override fun setTaskResult(taskResult: TaskResult?): Completable {
        taskResult?.let {
            taskResultMap.put(taskResult.taskUUID, taskResult)
        }
        return Completable.complete()
    }

    fun map(assessment: MFSAssessmentObject): Task {
        val steps = assessment.children.map { map(it) }

        return object : Task {
            override fun getIdentifier(): String {
                return assessment.identifier
            }

            override fun copyWithSteps(steps: MutableList<Step>?): Task {
                TODO("Not yet implemented")
            }

            override fun getSteps(): ImmutableList<Step> {
                return ImmutableList.copyOf(steps)
            }

            override fun getActions(): ImmutableMap<String, Action> {
                return ImmutableMap.of()
            }

            override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
                return ImmutableSet.of()
            }

            override fun getHiddenActions(): ImmutableSet<String> {
                return ImmutableSet.of()
            }

            override fun copyWithAsyncActions(asyncActions: MutableSet<AsyncActionConfiguration>?): Task {
                TODO("Not yet implemented")
            }

            override fun getProgressMarkers(): ImmutableList<String> {
                return if (assessment.progressMarkers == null) {
                    ImmutableList.of()
                } else {
                    ImmutableList.copyOf(assessment.progressMarkers!!)
                }
            }
        }
    }

    fun map(node: Node): Step {
        if (node is SRStep) {
            return WrappedAssessmentStep(node)
        }
        throw IllegalStateException("Expecting MFSAssessmentObject nodes to implement Step")
    }
}
