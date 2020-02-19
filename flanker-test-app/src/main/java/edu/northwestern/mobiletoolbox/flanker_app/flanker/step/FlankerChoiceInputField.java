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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

import org.sagebionetworks.research.domain.form.TextField.TextFieldOptions;
import org.sagebionetworks.research.domain.form.data_types.InputDataType;
import org.sagebionetworks.research.domain.form.interfaces.Choice;

public class FlankerChoiceInputField<E extends Comparable<E>> extends ChoiceInputFieldBase<E> {

    private final String flankerType;

    private final int delayToNextInputField;

    private final ImmutableList<String> flankers;

    private final String text;

    public FlankerChoiceInputField(final String identifier,
            final InputDataType formDataType,
            final String formUIHint, final String placeholderText, final String prompt,
            final String promptDetail, final Range range,
            final ImmutableList immutableList,
            final TextFieldOptions textFieldOptions,
            final ImmutableList<Choice<E>> choices, final E defaultAnswer, final boolean optional, final String flankerType, final int delayToNextInputField,
            final ImmutableList<String> flankers, final String text) {
        super(identifier, formDataType, formUIHint, placeholderText, prompt, promptDetail, range, immutableList,
                textFieldOptions, choices, defaultAnswer, optional);
        this.flankerType = flankerType;
        this.delayToNextInputField = delayToNextInputField;
        this.flankers = flankers;
        this.text = text;
    }

    public String getFlankerType() {
        return flankerType;
    }

    public int getDelayToNextInputField() {
        return delayToNextInputField;
    }

    public ImmutableList<String> getFlankers() {
        return flankers;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("flankerType", flankerType)
                .add("delayToNextInputField", delayToNextInputField)
                .add("flankers", flankers)
                .add("identifier", getIdentifier())
                .add("formDataType", getFormDataType())
                .add("formUIHint", getFormUIHint())
                .add("placeholderText", getPlaceholderText())
                .add("prompt", getPrompt())
                .add("promptDetail", getPromptDetail())
                .add("range", getRange())
                .add("surveyRules", getSurveyRules())
                .add("textFieldOptions", getTextFieldOptions())
                .add("choices", getChoices())
                .add("optional", isOptional())
                .add("text", text)
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
        if (!super.equals(o)) {
            return false;
        }
        final FlankerChoiceInputField that = (FlankerChoiceInputField) o;
        return delayToNextInputField == that.delayToNextInputField &&
                Objects.equal(flankerType, that.flankerType) &&
                Objects.equal(flankers, that.flankers) &&
                Objects.equal(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), flankerType, delayToNextInputField, flankers, text);
    }
}
