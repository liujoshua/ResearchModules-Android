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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

import org.sagebionetworks.research.domain.form.InputUIHint;
import org.sagebionetworks.research.domain.form.TextField.TextFieldOptions;
import org.sagebionetworks.research.domain.form.data_types.InputDataType;
import org.sagebionetworks.research.domain.form.interfaces.Choice;
import org.sagebionetworks.research.domain.form.interfaces.ChoiceOptions;
import org.sagebionetworks.research.domain.form.interfaces.InputField;
import org.sagebionetworks.research.domain.survey.SurveyRule;

/**
 * Base class for ChoiceInputField without AutoValue.
 *
 * @param <E>
 *         type of choices offered as options for the input field
 */
public class ChoiceInputFieldBase<E extends Comparable<E>> implements InputField<E>, ChoiceOptions<E> {

    @Nullable
    private final String identifier;

    @NonNull
    private final InputDataType formDataType;

    @InputUIHint
    @NonNull
    private final String formUIHint;

    @Nullable
    private final String placeholderText;

    @Nullable
    private final String prompt;

    @Nullable
    private final String promptDetail;

    @Nullable
    private final Range<E> range;

    @Nullable
    private final ImmutableList<? extends SurveyRule> surveyRules;

    @Nullable
    private final TextFieldOptions textFieldOptions;

    private final ImmutableList<Choice<E>> choices;

    @Nullable
    private final E defaultAnswer;

    private final boolean optional;

    public ChoiceInputFieldBase(final String identifier,
            @Nullable final InputDataType formDataType, final String formUIHint, final String placeholderText,
            final String prompt, final String promptDetail, final Range<E> range,
            final ImmutableList<? extends SurveyRule> surveyRules,
            final TextFieldOptions textFieldOptions,
            final ImmutableList<Choice<E>> choices, @Nullable final E defaultAnswer, final boolean optional) {
        this.identifier = identifier;
        this.formDataType = formDataType;
        this.formUIHint = formUIHint;
        this.placeholderText = placeholderText;
        this.prompt = prompt;
        this.promptDetail = promptDetail;
        this.range = range;
        this.surveyRules = surveyRules;
        this.textFieldOptions = textFieldOptions;
        this.choices = choices;
        this.defaultAnswer = defaultAnswer;
        this.optional = optional;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @NonNull
    @Override
    public InputDataType getFormDataType() {
        return formDataType;
    }

    @Nullable
    @Override
    public String getFormUIHint() {
        return formUIHint;
    }

    @Nullable
    @Override
    public String getPlaceholderText() {
        return placeholderText;
    }

    @Nullable
    @Override
    public String getPrompt() {
        return prompt;
    }

    @Nullable
    @Override
    public String getPromptDetail() {
        return promptDetail;
    }

    @Nullable
    @Override
    public Range<E> getRange() {
        return range;
    }

    @Nullable
    @Override
    public ImmutableList<? extends SurveyRule> getSurveyRules() {
        return surveyRules;
    }

    @Nullable
    @Override
    public TextFieldOptions getTextFieldOptions() {
        return textFieldOptions;
    }

    @Override
    public ImmutableList<Choice<E>> getChoices() {
        return choices;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Nullable
    @Override
    public E getDefaultAnswer() {
        return defaultAnswer;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("formDataType", formDataType)
                .add("formUIHint", formUIHint)
                .add("placeholderText", placeholderText)
                .add("prompt", prompt)
                .add("promptDetail", promptDetail)
                .add("range", range)
                .add("surveyRules", surveyRules)
                .add("textFieldOptions", textFieldOptions)
                .add("choices", choices)
                .add("optional", optional)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ChoiceInputFieldBase<?> that = (ChoiceInputFieldBase<?>) o;
        return optional == that.optional &&
                Objects.equal(identifier, that.identifier) &&
                Objects.equal(formDataType, that.formDataType) &&
                Objects.equal(formUIHint, that.formUIHint) &&
                Objects.equal(placeholderText, that.placeholderText) &&
                Objects.equal(prompt, that.prompt) &&
                Objects.equal(promptDetail, that.promptDetail) &&
                Objects.equal(range, that.range) &&
                Objects.equal(surveyRules, that.surveyRules) &&
                Objects.equal(textFieldOptions, that.textFieldOptions) &&
                Objects.equal(choices, that.choices);
    }

    @Override
    public int hashCode() {
        return Objects
                .hashCode(identifier, formDataType, formUIHint, placeholderText, prompt, promptDetail, range,
                        surveyRules,
                        textFieldOptions, choices, optional);
    }
}
