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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step.flanker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.sagebionetworks.research.domain.form.interfaces.InputField;
import org.sagebionetworks.research.domain.step.interfaces.Step;
import org.sagebionetworks.research.presentation.DisplayString;
import org.sagebionetworks.research.presentation.mapper.DrawableMapper;
import org.sagebionetworks.research.presentation.model.ColorThemeView;
import org.sagebionetworks.research.presentation.model.ImageThemeView;
import org.sagebionetworks.research.presentation.model.action.ActionView;
import org.sagebionetworks.research.presentation.model.implementations.UIStepViewBase;
import org.sagebionetworks.research.presentation.model.interfaces.UIStepView;

public class FlankerFormStepView implements UIStepView {

    @NonNull
    private final FlankerFormStep flankerFormStep;

    @NonNull
    private final UIStepView uiStepView;

    @NonNull
    public static FlankerFormStepView fromFlankerFormStep(@NonNull Step step, @NonNull DrawableMapper mapper) {
        UIStepView uiStepView = UIStepViewBase.fromUIStep(step, mapper);

        FlankerFormStep flankerFormStep = (FlankerFormStep) step;

        return new FlankerFormStepView(flankerFormStep, uiStepView);
    }

    public FlankerFormStepView(@NonNull final FlankerFormStep flankerFormStep,
            @NonNull final UIStepView uiStepView) {
        this.flankerFormStep = flankerFormStep;
        this.uiStepView = uiStepView;
    }

    @Override
    @Nullable
    public ActionView getActionFor(final String actionType) {
        return uiStepView.getActionFor(actionType);
    }

    @Override
    @NonNull
    public ImmutableMap<String, ActionView> getActions() {
        return uiStepView.getActions();
    }

    @Override
    @Nullable
    public ColorThemeView getColorTheme() {
        return uiStepView.getColorTheme();
    }

    @Override
    @Nullable
    public DisplayString getDetail() {
        return uiStepView.getDetail();
    }

    @Override
    @Nullable
    public DisplayString getFootnote() {
        return uiStepView.getFootnote();
    }

    @Override
    @Nullable
    public ImageThemeView getImageTheme() {
        return uiStepView.getImageTheme();
    }

    @Override
    @Nullable
    public DisplayString getText() {
        return uiStepView.getText();
    }

    @Override
    @Nullable
    public DisplayString getTitle() {
        return uiStepView.getTitle();
    }

    @Override
    @NonNull
    public String getIdentifier() {
        return uiStepView.getIdentifier();
    }

    @NonNull
    @Override
    public String getType() {
        return FlankerFormStep.TYPE;
    }

    @NonNull
    public ImmutableList<InputField> getInputFields() {
        return flankerFormStep.getInputFields();
    }

    @Nullable
    public String getStepName() {
        return flankerFormStep.getStepName();
    }

    @Nullable
    public String getStepGroup() {
        return flankerFormStep.getStepGroup();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", getIdentifier())
                .add("type", getType())
                .add("actions", getActions())
                .add("colorTheme", getColorTheme())
                .add("imageTheme", getImageTheme())
                .add("title", getTitle())
                .add("text", getText())
                .add("detail", getDetail())
                .add("footnote", getFootnote())
                .add("stepName", getStepName())
                .add("stepGroup", getStepGroup())
                .add("inputFields", getInputFields())

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
        final FlankerFormStepView that = (FlankerFormStepView) o;
        return Objects.equal(flankerFormStep, that.flankerFormStep) &&
                Objects.equal(uiStepView, that.uiStepView);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(flankerFormStep, uiStepView);
    }
}
