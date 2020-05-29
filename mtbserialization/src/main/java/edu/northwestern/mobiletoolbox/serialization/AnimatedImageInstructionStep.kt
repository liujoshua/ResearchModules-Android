/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.serialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sagebionetworks.assessmentmodel.ButtonAction
import org.sagebionetworks.assessmentmodel.ButtonActionInfo
import org.sagebionetworks.assessmentmodel.ImageInfo
import org.sagebionetworks.assessmentmodel.InstructionStep
import org.sagebionetworks.assessmentmodel.SpokenInstructionTiming
import org.sagebionetworks.assessmentmodel.serialization.StepObject

@Serializable
@SerialName("animatedImageInstruction")
data class AnimatedImageInstructionStep(
        override val identifier: String,
        override val fullInstructionsOnly: Boolean = false,
        override var detail: String? = null,
        val stepName: String? = null,
        val animatedImage: AnimatedImageInfo? = null,
        override val resultIdentifier: String? = null,
        override val buttonMap: Map<ButtonAction, ButtonActionInfo> = mapOf(),
        override val comment: String? = null,
        override val hideButtons: List<ButtonAction> =  listOf(),
        override val spokenInstructions: Map<SpokenInstructionTiming, String>? = null,
        override val subtitle: String?= null,
        override val title: String?= null,
        @SerialName("image")
        override val imageInfo: ImageInfo? = null
) : InstructionStep

@Serializable
data class AnimatedImageInfo(
        val type: String,
        val imageNames: List<String>,
        val placementType: String? = null,
        val animationRepeatCount: Int? = null,
        val animationDuration: Int? = null
)

