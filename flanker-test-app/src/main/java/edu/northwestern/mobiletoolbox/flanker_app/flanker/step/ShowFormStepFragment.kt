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

import android.view.View
import androidx.fragment.app.Fragment
import edu.northwestern.mobiletoolbox.flanker_app.R
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowStepFragmentBase
import org.sagebionetworks.research.mobile_ui.show_step.view.ShowUIStepFragmentBase
import org.sagebionetworks.research.mobile_ui.show_step.view.view_binding.UIStepViewBinding
import org.sagebionetworks.research.presentation.model.interfaces.StepView

class ShowFormStepFragment: ShowUIStepFragmentBase<FormStepView, ShowFormStepViewModel, UIStepViewBinding<FormStepView>>() {

    override fun getLayoutId(): Int {
        return R.layout.show_form_step_fragment
    }

    override fun instantiateAndBindBinding(view: View?): UIStepViewBinding<FormStepView> {
        return UIStepViewBinding(view)
    }
    
    companion object {
        @JvmStatic
        private fun make(stepView: StepView): ShowFormStepFragment {
            val fragment = ShowFormStepFragment()
            val args = ShowStepFragmentBase.createArguments(stepView)
            fragment.arguments = args
            return fragment
        }

        val newInstance = object: ShowStepFragmentFactory {
            override fun create(stepView: StepView): Fragment {
                return make(
                        stepView)
            }
        }
    }
}