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

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.sagebionetworks.research.domain.async.AsyncActionConfiguration;
import org.sagebionetworks.research.domain.form.interfaces.InputField;
import org.sagebionetworks.research.domain.step.interfaces.FormUIStep;
import org.sagebionetworks.research.domain.step.interfaces.Step;
import org.sagebionetworks.research.domain.step.ui.action.Action;
import org.sagebionetworks.research.domain.step.ui.theme.ColorTheme;
import org.sagebionetworks.research.domain.step.ui.theme.ImageTheme;

import java.util.Set;

@AutoValue
public abstract class FormStep implements FormUIStep {
	public static final String TYPE = "form";

	@AutoValue.Builder
	public abstract static class Builder {
		@NonNull
		public abstract FormStep build();

		@NonNull
		public abstract Builder setIdentifier(@NonNull String identifier);

		@NonNull
		public abstract Builder setInputFields(@NonNull ImmutableList<InputField> inputFields);

		@NonNull
		public abstract Builder setDetail(@Nullable String detail);

		@NonNull
		public abstract Builder setFootnote(@Nullable String footnote);

		@NonNull
		public abstract Builder setText(@Nullable String text);

		@NonNull
		public abstract Builder setTitle(@Nullable String title);

		@NonNull
		public abstract Builder setHiddenActions(@NonNull ImmutableSet<String> hiddenActions);

		@NonNull
		public abstract Builder setActions(@NonNull ImmutableMap<String, Action> actions);

		@NonNull
		public abstract Builder setAsyncActions(@NonNull Set<AsyncActionConfiguration> asyncActions);

		@NonNull
		public abstract Builder setColorTheme(@Nullable ColorTheme colorTheme);

		@NonNull
		public abstract Builder setImageTheme(@Nullable ImageTheme imageTheme);

		@NonNull
		public abstract Builder setStepName(@NonNull String stepName);

		@NonNull
		public abstract Builder setStepGroup(@NonNull String stepGroup);

		@NonNull
		public abstract Builder setIsPractice(boolean isPractice);

		// @todo("Finish")
	}

	public static Builder builder() {
		return new AutoValue_FormStep.Builder()
			.setActions(ImmutableMap.<String, Action>of())
			.setAsyncActions(ImmutableSet.<AsyncActionConfiguration>of());
	}

	public static TypeAdapter<FormStep> typeAdapter(Gson gson) {
		return new AutoValue_FormStep.GsonTypeAdapter(gson)
			.setDefaultActions(ImmutableMap.<String, Action>of())
			.setDefaultAsyncActions(ImmutableSet.<AsyncActionConfiguration>of())
			.setDefaultHiddenActions(ImmutableSet.<String>of());
	}

	@NonNull
	public String getType() {
		return TYPE;
	}

	@NonNull
	public abstract Builder toBuilder();

	@NonNull
	public abstract String getStepName();

	@NonNull
	public abstract String getStepGroup();

	public abstract boolean getIsPractice();

	@NonNull
	@Override
	public Step copyWithIdentifier(@NonNull final String identifier) {
		return toBuilder().setIdentifier(identifier).build();
	}
}
