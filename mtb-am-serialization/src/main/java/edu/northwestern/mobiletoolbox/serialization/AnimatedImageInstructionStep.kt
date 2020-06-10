package edu.northwestern.mobiletoolbox.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sagebionetworks.assessmentmodel.ImageInfo
import org.sagebionetworks.assessmentmodel.InstructionStep
import org.sagebionetworks.assessmentmodel.serialization.StepObject


@Serializable
@SerialName("animatedImageInstruction")
data class AnimatedImageInstructionStep(
    override val identifier: String,
    override val fullInstructionsOnly: Boolean = false,
    val animatedImage: AnimatedImageInfo? = null,
    val stepName: String? = null
) : MtbImageStep(), InstructionStep

@Serializable
data class AnimatedImageInfo(
    val type: String,
    val imageNames: List<String>? = null,
    val placementType: String? = null,
    val animationRepeatCount: Int? = null,
    val animationDuration: Int? = null
)

@Serializable
abstract class MtbImageStep(
    val timeout: Int? = null,
    @SerialName("text")
    override var detail: String? = null,
    override val resultIdentifier: String? = null,
    //TODO does not work image
    @SerialName("image")
    override val imageInfo: ImageInfo? = null
) : StepObject()

