/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Choice
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.StepResult

interface StepConfiguration {
    fun configureStep()
    fun startDelayForPresentation()
    fun stepIsReady()
    fun <V: Parcelable,S: Parcelable>createStepResult(choice: Choice<V, S>,
            identifier: String, practice: Boolean): StepResult
}
