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

package edu.northwestern.mobiletoolbox.flanker_app.flanker.inject;

import org.sagebionetworks.research.mobile_ui.inject.ShowStepFragmentScope;
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowActiveUIStepFragment;
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowCountdownStepFragment;
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowUIStepFragment;
import org.sagebionetworks.research.modules.common.step.instruction.ShowInstructionStepFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.overview.FlankerOverviewStepFragment;

@Module
public abstract class FlankerShowStepFragmentModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlankerShowStepFragmentModule.class);

    @ContributesAndroidInjector
    @ShowStepFragmentScope
    abstract ShowInstructionStepFragment contributeShowInstructionStepFragmentInjector();

    @ContributesAndroidInjector
    @ShowStepFragmentScope
    abstract FlankerOverviewStepFragment contributeShowFlankerOverviewStepFragmentInjector();

    // below are from SageResearch's ShowStepFragmentModule, with FormUIStepFragment commented out

    @ContributesAndroidInjector
    @ShowStepFragmentScope
    abstract ShowActiveUIStepFragment contributeShowActiveUIStepFragmentInjector();

    @ContributesAndroidInjector
    @ShowStepFragmentScope
    abstract ShowCountdownStepFragment contributeShowCountdownStepFragmentInjector();

    @ContributesAndroidInjector
    @ShowStepFragmentScope
    abstract ShowUIStepFragment contributeShowUIStepFragmentInjector();

//    @ContributesAndroidInjector
//    @ShowStepFragmentScope
//    abstract FormUIStepFragment contributeFormUIStepFragmentInjector();
//
}
