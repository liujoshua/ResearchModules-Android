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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step.form;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import org.sagebionetworks.research.domain.async.AsyncActionConfiguration;
import org.sagebionetworks.research.domain.form.interfaces.InputField;
import org.sagebionetworks.research.domain.step.interfaces.Step;
import org.sagebionetworks.research.domain.step.ui.action.Action;
import org.sagebionetworks.research.domain.step.ui.theme.ColorTheme;
import org.sagebionetworks.research.domain.step.ui.theme.ImageTheme;

public class FlankerFormStep extends MtbFormStepBase {
    public static final String TYPE = "form";

    @Nullable
    private final String stepName;

    @Nullable
    private final String stepGroup;

    public FlankerFormStep(@NonNull final String identifier,
            @Nullable final ImmutableList<InputField> inputFields,
            @Nullable final ColorTheme colorTheme,
            @Nullable final ImageTheme imageTheme,
            @Nullable final String detail,
            @Nullable final String footnote,
            @Nullable final String text,
            @Nullable final String title,
            @Nullable final ImmutableSet<AsyncActionConfiguration> asyncActions,
            @Nullable final ImmutableMap<String, Action> actions,
            @Nullable final ImmutableSet<String> hiddenActions,
            @Nullable final String stepName,
            @Nullable final String stepGroup) {
        super(identifier, inputFields, colorTheme, imageTheme, detail, footnote, text, title, asyncActions, actions,
                hiddenActions);
        this.stepName = stepName;
        this.stepGroup = stepGroup;
    }

    @NonNull
    @Override
    public String getType() {
        return TYPE;
    }

    @NonNull
    @Override
    public Step copyWithIdentifier(@NonNull final String identifier) {
        return new FlankerFormStep(identifier, getInputFields(), getColorTheme(), getImageTheme(), getDetail(),
                getFootnote(), getText(), getTitle(), getAsyncActions(), getActions(), getHiddenActions(), stepName,
                stepGroup);
    }

    @Nullable
    public String getStepName() {
        return stepName;
    }

    @Nullable
    public String getStepGroup() {
        return stepGroup;
    }

    @NonNull
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("stepName", stepName)
                .add("stepGroup", stepGroup)
                .add("type", getType())
                .add("identifier", getIdentifier())
                .add("inputFields", getInputFields())
                .add("colorTheme", getColorTheme())
                .add("imageTheme", getImageTheme())
                .add("detail", getDetail())
                .add("footnote", getFootnote())
                .add("text", getText())
                .add("title", getTitle())
                .add("asyncActions", getAsyncActions())
                .add("actions", getActions())
                .add("hiddenActions", getHiddenActions())
                .toString();
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final FlankerFormStep that = (FlankerFormStep) o;
        return Objects.equal(stepName, that.stepName) &&
                Objects.equal(stepGroup, that.stepGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), stepName, stepGroup);
    }
}
