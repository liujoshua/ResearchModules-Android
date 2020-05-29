/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.serialization.dccs

import edu.northwestern.mobiletoolbox.serialization.IntIntChoiceInputField
import edu.northwestern.mobiletoolbox.serialization.MtbNodeObject
import edu.northwestern.mobiletoolbox.serialization.MtbStep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.sagebionetworks.assessmentmodel.Assessment
import org.sagebionetworks.assessmentmodel.AssessmentResult
import org.sagebionetworks.assessmentmodel.FormStep
import org.sagebionetworks.assessmentmodel.InstructionStep
import org.sagebionetworks.assessmentmodel.Node
import org.sagebionetworks.assessmentmodel.NodeContainer
import org.sagebionetworks.assessmentmodel.resourcemanagement.FileLoader
import org.sagebionetworks.assessmentmodel.resourcemanagement.ResourceInfo
import org.sagebionetworks.assessmentmodel.resourcemanagement.copyResourceInfo
import org.sagebionetworks.assessmentmodel.serialization.NodeContainerObject

@Serializable
@SerialName("Dimensional_change_card_sort")
data class DCCSAssessmentObject(
        override val identifier: String = "",
        @SerialName("steps")
        override val children: List<Node>,
        val taskOrientation: String = "portait",
        val defaultStepRules: DefaultSteps? = null,
        val failRules: List<FailRule> = listOf(),
        override val versionString: String? = null,
        @SerialName("taskTimeLimit")
        override var estimatedMinutes: Int = 0,
        override val resultIdentifier: String? = null
) : MtbNodeObject(), NodeContainer, Assessment {
    override val progressMarkers: kotlin.collections.List<kotlin.String>?
        get() = null
    override val imageInfo: org.sagebionetworks.assessmentmodel.ImageInfo?
        get() = null
    override fun createResult(): AssessmentResult = super<Assessment>.createResult()
    override fun unpack(
            fileLoader: FileLoader,
            resourceInfo: ResourceInfo,
            jsonCoder: Json
    ): DCCSAssessmentObject {
        imageInfo?.copyResourceInfo(resourceInfo)
        val copyChildren = children.map { it.unpack(fileLoader, resourceInfo, jsonCoder) }
        val copy = copy(children = copyChildren)
        copy.copyFrom(this)
        return copy
    }
}

@Serializable
@SerialName("dccsForm")
data class DCCSForm(
        override val identifier: String,
        val failRuleSection: String? = null,
        val isPractice: Boolean = false,
        @SerialName("inputFields")
        override val children: List<IntIntChoiceInputField> = listOf(),
        val steps: List<Node> = listOf(),
        val stimulusText: String? = null,
        val screenType: String? = null,
        val isAnimated: Boolean = false,
        val timing: AnimatedTiming? = null
) : MtbStep(), FormStep

@Serializable
@SerialName("dccsFormStimulusImage")
data class DCCSFormStimulusImage(
        override val identifier: String,
        val failRuleSection: String? = null,
        val isPractice: Boolean = false,
        @SerialName("inputFields")
        override val children: List<IntIntChoiceInputField> = listOf(),
        val stimulusImage: String? = null,
        val screenType: String? = null,
        val feedback: Feedback? = null
) : MtbStep(), FormStep

@Serializable
@SerialName("mtbActive")
data class DCCSActive(
        override val identifier: String,
        override var fullInstructionsOnly: Boolean = false
) : MtbStep(),
        InstructionStep


@Serializable
data class DefaultSteps(
        val numberOfLiveStepsToAdminister: Int
)

@Serializable
data class AnimatedTiming(
        val animationDuration: Int
)

@Serializable
data class FailRule(
        val description: String,
        val failRuleSection: String,
        val failThreshold: Int
)

@Serializable
data class Feedback(
        val correctButtonImageName: String,
        val correctFeedback: String,
        val correctStimulusImageName: String,
        val incorrectButtonImageName: String,
        val incorrectFeedback: String,
        val incorrectStimulusImageName: String
)
