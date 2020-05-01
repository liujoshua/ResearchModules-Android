/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.Choice
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.StepResult

interface RecordChoice {

    fun <V: Parcelable,S: Parcelable>recordChoice(choice: Choice<V, S>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordChoice(choices: ArrayList<Choice<V, S>>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordNoChoice(choice: Choice<V, S>, practice: Boolean)
}
