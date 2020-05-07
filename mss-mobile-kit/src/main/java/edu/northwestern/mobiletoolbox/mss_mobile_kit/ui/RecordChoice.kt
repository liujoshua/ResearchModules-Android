/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.data.MssChoice

interface RecordChoice {

    fun <V: Parcelable,S: Parcelable>recordChoice(choice: MssChoice<V, S>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordChoice(choices: ArrayList<MssChoice<V, S>>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordNoChoice(choice: MssChoice<V, S>, practice: Boolean)
}
