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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.step

import android.util.Log
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import org.sagebionetworks.research.domain.RuntimeTypeAdapterFactory
import org.sagebionetworks.research.domain.inject.GsonModule.createPassThroughDeserializer
import org.sagebionetworks.research.domain.inject.StepModule.StepClassKey
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.StepViewKey
import org.sagebionetworks.research.presentation.inject.ShowStepViewModelModule.StepViewClassKey
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.inject.StepViewModule.StepTypeKey

@Module
class FormStepModule {
	@Module
	companion object {
		@Provides
		@IntoMap
		@StepClassKey(FormStep::class)
		fun provideFormStepTypeKey(): String = FormStep.TYPE_KEY

		@Provides
		@IntoMap
		@ClassKey(FormStep::class)
		fun provideFormStepDeserializer(): JsonDeserializer<FormStep> = createPassThroughDeserializer(
				FormStep::class.java)

		@Provides
		@IntoMap
		@StepTypeKey(FormStep.TYPE_KEY)
		fun provideFormStepViewFactory(): InternalStepViewFactory = FormStepView.fromFormStep

		@Provides
		@IntoMap
		@StepViewKey(
				FormStepView.TYPE)
		fun provideFormStepFactory(): ShowStepFragmentFactory = ShowFormStepFragment.newInstance

		@Provides
		@IntoMap
		@StepViewClassKey(
				FormStepView.TYPE)
		fun provideFormStepVMF() = ShowFormStepViewModelFactory()

		@Provides
		@IntoSet
		fun provideType(stepClassKeys: Map<Class<out Step>, String>): RuntimeTypeAdapterFactory<out Step> {
			val stepAdapterFactory: RuntimeTypeAdapterFactory<Step> = RuntimeTypeAdapterFactory.of(Step::class.java, Step.KEY_TYPE)
			stepClassKeys.entries.forEach {
				Log.d("FormStepModule", "Registering key: ${it.key} value: ${it.value}")
				stepAdapterFactory.registerSubtype(it.key, it.value)
			}

			return stepAdapterFactory
		}
	}

}
