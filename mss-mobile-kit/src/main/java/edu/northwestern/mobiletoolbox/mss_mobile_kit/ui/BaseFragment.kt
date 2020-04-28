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

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimeManager
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimeManagerImpl
import edu.northwestern.mobiletoolbox.mss_mobile_toolkit.timer.TimerImpl

abstract class BaseFragment : Fragment(), ComponentAvailability, StepConfiguration {

    //TODO make it injected
    private val timeManager: TimeManager = TimeManagerImpl()
    private val presentationTimer = TimerImpl()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponentsVisibility(false)
        enableUI(false)
        configureStep()
        //TODO initialize timer manager
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        startDelayForPresentation()
    }

    override fun startDelayForPresentation() {
        fragmentComponentsVisibility(true)
        //performAnimatedSequence it'a a call that animate
    }


    //Needs to be an event that will call this methodin the end of presentation
    override fun stepIsReady() {
        enableUI(true)
        timeManager.startResponse()
    }

    override fun onPause() {
        super.onPause()
        //here is method for pause any instruction that should be done when you pause
    }

    override fun onDestroy() {
        super.onDestroy()
        //maybe create class that will force clean data
    }
}
