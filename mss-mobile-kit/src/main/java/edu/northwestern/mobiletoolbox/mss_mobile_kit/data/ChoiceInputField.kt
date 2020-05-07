/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import com.google.common.collect.Range
import org.sagebionetworks.research.domain.form.InputUIHint
import org.sagebionetworks.research.domain.form.TextField.TextFieldOptions
import org.sagebionetworks.research.domain.form.data_types.InputDataType
import org.sagebionetworks.research.domain.form.implementations.InputFieldBase
import org.sagebionetworks.research.domain.form.interfaces.ChoiceOptions
import org.sagebionetworks.research.domain.form.interfaces.Choice as RSDChoice

//TODO rename _choice to getChoices returns _choice
open class ChoiceInputField<ValueType, ScoreType>(
        private val identifier: String,
        private val choices: List<MssChoice<ValueType, ScoreType>>,
        private val dataType: InputDataType,
        private val uiHint: InputUIHint? = null,
        private val  prompt: String? = null,
        private val defaultAnswer: ValueType? = null,
        private val textFieldOptions: TextFieldOptions? = null,
        private val placeholderText: String? = null,
        private val promptDetail: String? = null,
        private val range: Range<ValueType>? = null
)
    : ChoiceOptions<ValueType>, InputFieldBase<ValueType>()
        where ValueType : Comparable<ValueType>, ValueType : Parcelable, ScoreType : Parcelable {


    override fun getChoices(): ImmutableList<RSDChoice<ValueType>> {
        return ImmutableList.copyOf(this.choices)
    }

    override fun isOptional(): Boolean {
        return isOptional
    }

    override fun getDefaultAnswer(): ValueType? {
        return defaultAnswer
    }

    override fun getTextFieldOptions(): TextFieldOptions? {
        return textFieldOptions
    }

    override fun getIdentifier(): String? {
        return identifier
    }

    override fun getFormDataType(): InputDataType {
        return dataType
    }

    override fun getPlaceholderText(): String? {
        return placeholderText
    }

    override fun getPromptDetail(): String? {
        return promptDetail
    }

    override fun getRange(): Range<ValueType>? {
        return range
    }

    override fun getFormUIHint(): String? {
        return uiHint.toString()
    }

    override fun getPrompt(): String? {
        return prompt
    }
}




