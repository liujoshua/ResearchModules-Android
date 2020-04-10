/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import com.google.common.collect.Range
import org.sagebionetworks.research.domain.form.TextField.TextFieldOptions
import org.sagebionetworks.research.domain.form.data_types.InputDataType
import org.sagebionetworks.research.domain.form.interfaces.ChoiceOptions
import org.sagebionetworks.research.domain.form.interfaces.InputField
import org.sagebionetworks.research.domain.survey.SurveyRule
import org.sagebionetworks.research.domain.form.interfaces.Choice as RSDChoice

//TODO not sure that "ValueType: Comparable" = IOS Equatable
//TODO what generic we need to use <ValueType , Choice: Parcelable> ?!
open class  ChoiceInputField <ValueType , Choice: Parcelable>
    : ChoiceOptions<Choice>, InputField<ValueType>
where ValueType: Comparable<ValueType>?,  ValueType : Parcelable {

    override fun getChoices(): ImmutableList<RSDChoice<Choice>> {
       return this.choices
    }

    override fun isOptional(): Boolean {
      return isOptional
    }

    override fun getDefaultAnswer(): Choice? {
       return defaultAnswer
    }

    override fun getTextFieldOptions(): TextFieldOptions? {
      return textFieldOptions
    }

    override fun getIdentifier(): String? {
       return identifier
    }

    override fun getSurveyRules(): ImmutableList<out SurveyRule>? {
        return  surveyRules
    }

    override fun getFormDataType(): InputDataType {
        return  formDataType
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
       return formUIHint
    }

    override fun getPrompt(): String? {
       return prompt
    }
}