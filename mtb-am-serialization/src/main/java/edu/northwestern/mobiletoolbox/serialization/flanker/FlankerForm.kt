package edu.northwestern.mobiletoolbox.serialization.flanker

import edu.northwestern.mobiletoolbox.serialization.IntIntChoiceInputField
import edu.northwestern.mobiletoolbox.serialization.MtbNodeObject
import edu.northwestern.mobiletoolbox.serialization.MtbStep
import edu.northwestern.mobiletoolbox.serialization.MtbStepRule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.sagebionetworks.assessmentmodel.*
import org.sagebionetworks.assessmentmodel.resourcemanagement.FileLoader
import org.sagebionetworks.assessmentmodel.resourcemanagement.ResourceInfo
import org.sagebionetworks.assessmentmodel.resourcemanagement.copyResourceInfo


@Serializable
@SerialName("Flanker")
data class FlankerAssessmentObject(
    override val identifier: String,
    @SerialName("steps")
    override val children: List<Node>,
    override val versionString: String? = null,
    override val resultIdentifier: String? = null,
    override var estimatedMinutes: Int = 0,
    val taskOrientation: String = "portrait",
    val defaultSteps: DefaultSteps? = null,
    val stepRules: List<MtbStepRule> = listOf()
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
    ): FlankerAssessmentObject {
        imageInfo?.copyResourceInfo(resourceInfo)
        val copyChildren = children.map { it.unpack(fileLoader, resourceInfo, jsonCoder) }
        val copy = copy(children = copyChildren)
        copy.copyFrom(this)
        return copy
    }
}

//@Serializable
//@SerialName("flankerParentForm")
//data class FlankerParentForm(
//    override val identifier: String,
//    val isPractice: Boolean = false,
//    @SerialName("steps")
//    val children: List<Node> = listOf()
//) : MtbStep()

@Serializable
@SerialName("flankerForm")
data class FlankerForm(
    override val identifier: String,
    val isPractice: Boolean = false,
    @SerialName("inputFields")
    override val children: List<Node> = listOf(),
    //Could remove this and use above "flankerParentForm" instead
    val steps: List<Node> = listOf()
) : MtbStep(), FormStep

@Serializable
@SerialName("flankerInstructionForm")
data class FlankerInstructionForm(
    override val identifier: String,
    @SerialName("inputFields")
    override val children: List<IntIntChoiceInputField> = listOf(),
    val isInstruction: Boolean = true,
    val flankerImageNames: List<String> = listOf(),
    val flankerType: String? = null
) : MtbStep(), FormStep

//Can 'flankerInstruction' steps with 'inputFields' be changed to 'flankerInstructionForm'?
@Serializable
@SerialName("flankerInstruction")
data class FlankerInstructionStep(
    override val identifier: String,
    override val fullInstructionsOnly: Boolean = false,
    val branchingNavigationRules: List<MtbStepRule> = listOf(),
    val flankerImageNames: List<String> = listOf(),
    val htmlText: String? = null,
    //val inputFields: List<Node> = listOf(),
    val stepBackTo: String? = null,
    val stepGroup: String? = null,
    val stepName: String? = null
) : MtbStep(), InstructionStep

@Serializable
@SerialName("flankerOverview")
data class FlankerOverviewStep(
    override val identifier: String,
    override val fullInstructionsOnly: Boolean = false,
    val branchingNavigationRules: List<MtbStepRule> = listOf()
) : MtbStep(), InstructionStep


@Serializable
data class DefaultSteps(
    val numberOfSteps: Int,
    val startingStepGroup: String
)
