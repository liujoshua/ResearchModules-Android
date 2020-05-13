/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.data.MtbChoice
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
        val testChoice = MtbChoice<Test, Test>(Test(), Test(), UUID(0, 0), "", "", "", "", "")
        recordChoice(testChoice,false)
    }
}
