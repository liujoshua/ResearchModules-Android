/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.number_match

import edu.northwestern.mobiletoolbox.IntIntChoiceInputField
import edu.northwestern.mobiletoolbox.MtbStep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.sagebionetworks.assessmentmodel.Assessment
import org.sagebionetworks.assessmentmodel.AssessmentResult
import org.sagebionetworks.assessmentmodel.FormStep
import org.sagebionetworks.assessmentmodel.Node
import org.sagebionetworks.assessmentmodel.resourcemanagement.FileLoader
import org.sagebionetworks.assessmentmodel.resourcemanagement.ResourceInfo
import org.sagebionetworks.assessmentmodel.resourcemanagement.copyResourceInfo
import org.sagebionetworks.assessmentmodel.serialization.NodeContainerObject

@Serializable
@SerialName("Number_Match")
data class NumberMatchAssessmentObject(
        override val identifier: String = "",
        @SerialName("steps")
        override val children: List<Node>,
        val taskOrientation: String = "landscape",
        val symbolArray: List<String>,
        val alertMessages: List<AlertMessages>,
        override val versionString: String? = null,
        @SerialName("taskTimeLimit")
        override val estimatedMinutes: Int = 0,
        override val resultIdentifier: String? = null
) : NodeContainerObject(), Assessment {
    override fun createResult(): AssessmentResult = super<Assessment>.createResult()
    override fun unpack(
            fileLoader: FileLoader,
            resourceInfo: ResourceInfo,
            jsonCoder: Json
    ): NumberMatchAssessmentObject {
        imageInfo?.copyResourceInfo(resourceInfo)
        val copyChildren = children.map { it.unpack(fileLoader, resourceInfo, jsonCoder) }
        val copy = copy(children = copyChildren)
        copy.copyFrom(this)
        return copy
    }
}


@Serializable
@SerialName("numberMatchForm")
data class NumberMatchForm(
        override val identifier: String,
        val isPractice: Boolean = false,
        val prefillSymbols: List<String>? = null,
        @SerialName("inputFields")
        override val children: List<Node> = listOf(),
        val steps: List<Node> = listOf()
) : MtbStep(), FormStep

@Serializable
@SerialName("numberMatchInstructionForm")
data class NumberMatchInstructionForm(
        override val identifier: String,
        @SerialName("inputFields")
        override val children: List<IntIntChoiceInputField> = listOf(),
        val symbol: String? = null
) : MtbStep(), FormStep

@Serializable
data class AlertMessages(
        val type: String,
        val title: String,
        val message: String
)


