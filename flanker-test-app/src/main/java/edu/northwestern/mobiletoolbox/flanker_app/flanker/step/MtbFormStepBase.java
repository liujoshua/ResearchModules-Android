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

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import org.jetbrains.annotations.NotNull;
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration;
import org.sagebionetworks.research.domain.form.interfaces.InputField;
import org.sagebionetworks.research.domain.step.interfaces.FormUIStep;
import org.sagebionetworks.research.domain.step.ui.action.Action;
import org.sagebionetworks.research.domain.step.ui.theme.ColorTheme;
import org.sagebionetworks.research.domain.step.ui.theme.ImageTheme;

public abstract class MtbFormStepBase implements FormUIStep {
    @NonNull
    private final String identifier;

    @NotNull
    private final ImmutableList<InputField> inputFields;

    @Nullable
    private final ColorTheme colorTheme;

    @Nullable
    private final ImageTheme imageTheme;

    @Nullable
    private final String detail;

    @Nullable
    private final String footnote;

    @Nullable
    private final String text;

    @Nullable
    private final String title;

    @NonNull
    private final ImmutableSet<AsyncActionConfiguration> asyncActions;

    @NonNull
    private final ImmutableMap<String, Action> actions;

    @NonNull
    private final ImmutableSet<String> hiddenActions;

    public MtbFormStepBase(@NonNull final String identifier,
            @Nullable final ImmutableList<InputField> inputFields,
            @Nullable final ColorTheme colorTheme,
            @Nullable final ImageTheme imageTheme,
            @Nullable final String detail,
            @Nullable final String footnote,
            @Nullable final String text,
            @Nullable final String title,
            @Nullable final ImmutableSet<AsyncActionConfiguration> asyncActions,
            @Nullable final ImmutableMap<String, Action> actions,
            @Nullable final ImmutableSet<String> hiddenActions) {
        this.identifier = identifier;
        this.inputFields = inputFields != null ? inputFields : ImmutableList.of();
        this.colorTheme = colorTheme;
        this.imageTheme = imageTheme;
        this.detail = detail;
        this.footnote = footnote;
        this.text = text;
        this.title = title;
        this.asyncActions = asyncActions != null ? asyncActions : ImmutableSet.of();
        this.actions = actions != null ? actions : ImmutableMap.of();
        this.hiddenActions = hiddenActions != null ? hiddenActions : ImmutableSet.of();
    }

    @NonNull
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @NonNull
    @Override
    public ImmutableList<InputField> getInputFields() {
        return inputFields;
    }

    @Nullable
    @Override
    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    @Nullable
    @Override
    public ImageTheme getImageTheme() {
        return imageTheme;
    }

    @Nullable
    @Override
    public String getDetail() {
        return detail;
    }

    @Nullable
    @Override
    public String getFootnote() {
        return footnote;
    }

    @Nullable
    @Override
    public String getText() {
        return text;
    }

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @NonNull
    @Override
    public ImmutableSet<AsyncActionConfiguration> getAsyncActions() {
        return asyncActions;
    }

    @NonNull
    @Override
    public ImmutableMap<String, Action> getActions() {
        return actions;
    }

    @NonNull
    @Override
    public ImmutableSet<String> getHiddenActions() {
        return hiddenActions;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MtbFormStepBase that = (MtbFormStepBase) o;
        return Objects.equal(identifier, that.identifier) &&
                Objects.equal(inputFields, that.inputFields) &&
                Objects.equal(colorTheme, that.colorTheme) &&
                Objects.equal(imageTheme, that.imageTheme) &&
                Objects.equal(detail, that.detail) &&
                Objects.equal(footnote, that.footnote) &&
                Objects.equal(text, that.text) &&
                Objects.equal(title, that.title) &&
                Objects.equal(asyncActions, that.asyncActions) &&
                Objects.equal(actions, that.actions) &&
                Objects.equal(hiddenActions, that.hiddenActions);
    }

    @Override
    public int hashCode() {
        return Objects
                .hashCode(identifier, inputFields, colorTheme, imageTheme, detail, footnote, text, title,
                        asyncActions,
                        actions, hiddenActions);
    }
}
