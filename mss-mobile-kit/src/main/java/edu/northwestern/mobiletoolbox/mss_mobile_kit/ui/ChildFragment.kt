/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Choice
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.StepResult
import kotlinx.android.parcel.Parcelize
import java.util.UUID

class ChildFragment : BaseFragment() {
    @Parcelize
    data class Test(val testField: String = "") : Parcelable

    override fun fragmentComponentsVisibility(visible: Boolean) {
    }

    override fun enableUI(enable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun configureStep() {
        TODO("Not yet implemented")
    }

    fun prepareTestStepResult() {
        val testChoice = Choice<Test, Test>(Test(), Test(), UUID(0, 0), "", "", "", "", "")
        createStepResult(testChoice, "", false)
    }

    override fun <V : Parcelable, S : Parcelable> createStepResult(choice: Choice<V, S>, identifier: String,
            practice: Boolean): StepResult {
        //Store this data. Should be in ViewModel
        TODO("Not yet implemented")
    }
}
