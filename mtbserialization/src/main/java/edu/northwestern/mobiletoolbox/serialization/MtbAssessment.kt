/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.serialization

import edu.northwestern.mobiletoolbox.serialization.dccs.DCCSActive
import edu.northwestern.mobiletoolbox.serialization.dccs.DCCSAssessmentObject
import edu.northwestern.mobiletoolbox.serialization.dccs.DCCSForm
import edu.northwestern.mobiletoolbox.serialization.dccs.DCCSFormStimulusImage
import edu.northwestern.mobiletoolbox.serialization.flanker.FlankerAssessmentObject
import edu.northwestern.mobiletoolbox.serialization.flanker.FlankerForm
import edu.northwestern.mobiletoolbox.serialization.flanker.FlankerInstructionForm
import edu.northwestern.mobiletoolbox.serialization.flanker.FlankerInstructionStep
import edu.northwestern.mobiletoolbox.serialization.flanker.FlankerOverviewStep
import edu.northwestern.mobiletoolbox.serialization.mfs.MFSAssessmentObject
import edu.northwestern.mobiletoolbox.serialization.mfs.MfsForm
import edu.northwestern.mobiletoolbox.serialization.mfs.MfsOverviewStep
import edu.northwestern.mobiletoolbox.serialization.number_match.NumberMatchAssessmentObject
import edu.northwestern.mobiletoolbox.serialization.number_match.NumberMatchForm
import edu.northwestern.mobiletoolbox.serialization.number_match.NumberMatchInstructionForm
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import org.sagebionetworks.assessmentmodel.Assessment
import org.sagebionetworks.assessmentmodel.ButtonAction
import org.sagebionetworks.assessmentmodel.ButtonActionInfo
import org.sagebionetworks.assessmentmodel.ContentNode
import org.sagebionetworks.assessmentmodel.ImageInfo
import org.sagebionetworks.assessmentmodel.Node
import org.sagebionetworks.assessmentmodel.SpokenInstructionTiming
import org.sagebionetworks.assessmentmodel.Step
import org.sagebionetworks.assessmentmodel.navigation.DirectNavigationRule
import org.sagebionetworks.assessmentmodel.serialization.FetchableImage
import org.sagebionetworks.assessmentmodel.serialization.InstructionStepObject
import org.sagebionetworks.assessmentmodel.serialization.Serialization
import org.sagebionetworks.assessmentmodel.serialization.ViewThemeObject
import org.sagebionetworks.assessmentmodel.survey.ChoiceOption

object MtbSerialization {
    object SerializersModule {
        val default =
                Serialization.SerializersModule.default +
                        dccsSerializersModule +
                        flankerSerializersModule +
                        mfsSerializersModule +
                        numberMatchSerializersModule
    }

    object JsonCoder {
        val default = Json(
                context = SerializersModule.default,
                configuration = JsonConfiguration.Stable
        )
    }
}

val flankerSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        FlankerAssessmentObject::class with FlankerAssessmentObject.serializer()
        FlankerOverviewStep::class with FlankerOverviewStep.serializer()
        FlankerForm::class with FlankerForm.serializer()
        //FlankerParentForm::class with FlankerParentForm.serializer()
        FlankerInstructionStep::class with FlankerInstructionStep.serializer()
        FlankerInstructionForm::class with FlankerInstructionForm.serializer()
        IntIntChoiceInputField::class with IntIntChoiceInputField.serializer()
    }
    polymorphic(Assessment::class) {
        FlankerAssessmentObject::class with FlankerAssessmentObject.serializer()
    }
}


val mfsSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        MFSAssessmentObject::class with MFSAssessmentObject.serializer()
        MfsOverviewStep::class with MfsOverviewStep.serializer()
        MfsForm::class with MfsForm.serializer()
        InstructionStepObject::class with InstructionStepObject.serializer()
        StringIntChoiceInputField::class with StringIntChoiceInputField.serializer()
    }
    polymorphic(Assessment::class) {
        MFSAssessmentObject::class with MFSAssessmentObject.serializer()
    }
}


val numberMatchSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        NumberMatchAssessmentObject::class with NumberMatchAssessmentObject.serializer()
        InstructionStepObject::class with InstructionStepObject.serializer()
        NumberMatchForm::class with NumberMatchForm.serializer()
        NumberMatchInstructionForm::class with NumberMatchInstructionForm.serializer()
        AnimatedImageInstructionStep::class with AnimatedImageInstructionStep.serializer()
        IntIntChoiceInputField::class with IntIntChoiceInputField.serializer()
    }
    polymorphic(Assessment::class) {
        NumberMatchAssessmentObject::class with NumberMatchAssessmentObject.serializer()
    }
}


val dccsSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        DCCSAssessmentObject::class with DCCSAssessmentObject.serializer()
        InstructionStepObject::class with InstructionStepObject.serializer()
        DCCSForm::class with DCCSForm.serializer()
        DCCSFormStimulusImage::class with DCCSFormStimulusImage.serializer()
        AnimatedImageInstructionStep::class with AnimatedImageInstructionStep.serializer()
        DCCSActive::class with DCCSActive.serializer()
        IntIntChoiceInputField::class with IntIntChoiceInputField.serializer()
    }
    polymorphic(Assessment::class) {
        DCCSAssessmentObject::class with DCCSAssessmentObject.serializer()
    }
}


val flankerStringIntSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        StringIntChoiceInputField::class with StringIntChoiceInputField.serializer()
    }
}
val flankerIntIntSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        IntIntChoiceInputField::class with IntIntChoiceInputField.serializer()
    }
}
val flankerDoubleIntSerializersModule = SerializersModule {
    polymorphic(Node::class) {
        DoubleIntChoiceInputField::class with DoubleIntChoiceInputField.serializer()
    }
}


@Serializable
abstract class MtbChoiceInputField<ValueType, ScoreType> : Node {
    abstract val choices: List<MtbChoiceOption<ValueType, ScoreType>>
    var symbol: String? = null

    // Not used
    override val resultIdentifier: String?
        get() = null
    override val comment: String?
        get() = null
    override val hideButtons: List<ButtonAction>
        get() = listOf()
    override val buttonMap: Map<ButtonAction, ButtonActionInfo>
        get() = mapOf()
}

@Serializable
@SerialName("singleChoiceStringInt")
data class StringIntChoiceInputField(
        override val identifier: String,
        override val choices: List<MtbChoiceOption<String, Int>>
) : MtbChoiceInputField<String, Int>()

@Serializable
@SerialName("singleChoice")
data class IntIntChoiceInputField(
        override val identifier: String,
        override val choices: List<MtbChoiceOption<Int, Int>>
) : MtbChoiceInputField<Int, Int>()

@Serializable
@SerialName("singleChoiceDoubleInt")
data class DoubleIntChoiceInputField(
        override val identifier: String,
        override val choices: List<MtbChoiceOption<Double, Int>>
) : MtbChoiceInputField<Double, Int>()

@Serializable
data class MtbChoiceOption<ValueType, ScoreType>(
        val value: ValueType,
        val score: ScoreType? = null,
        val itemResponseOID: String,
        @SerialName("text")
        override val fieldLabel: String? = null,
        @SerialName("image")
        val image: String? = null,
        override val icon: FetchableImage? = null,
        override val exclusive: Boolean = false
) : ChoiceOption {
    override fun jsonValue(selected: Boolean): JsonElement? = when (value) {
        is Number -> JsonPrimitive(value)
        else -> JsonPrimitive("$value")
    }
}

@Serializable
data class MtbStepRule(
        val criteria: List<MtbCriteria>,
        val description: String,
        val nextStepName: String,
        val stepGroup: String? = null
)

@Serializable
data class MtbCriteria(
        val description: String,
        val duration: Int? = null,
        val name: String
)

@Serializable
abstract class MtbStep(
        val timeout: Int? = null,
        override val resultIdentifier: String? = null,
        @SerialName("image")
        override val imageInfo: ImageInfo? = null
) : MtbNodeObject(), Step, ContentNode {
    override var viewTheme: ViewThemeObject? = null
    override var spokenInstructions: Map<SpokenInstructionTiming, String>? = null
}

@Serializable
abstract class MtbNodeObject : ContentNode, DirectNavigationRule {
    override var comment: String? = null
    override var title: String? = null
    override var subtitle: String? = null
    @SerialName("text")
    override var detail: String? = null
    override var footnote: String? = null
    @SerialName("shouldHideActions")
    override var hideButtons: List<ButtonAction> = listOf()
    @SerialName("actions")
    override var buttonMap: Map<ButtonAction, ButtonActionInfo> = mapOf()

    @SerialName("nextStepIdentifier")
    override var nextNodeIdentifier: String? = null

    open fun copyFrom(original: ContentNode) {
        this.comment = original.comment
        this.title = original.title
        this.subtitle = original.subtitle
        this.detail = original.detail
        this.footnote = original.footnote
        this.hideButtons = original.hideButtons
        this.buttonMap = original.buttonMap
    }
}
