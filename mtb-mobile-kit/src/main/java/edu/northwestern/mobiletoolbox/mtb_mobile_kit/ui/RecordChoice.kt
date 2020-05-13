/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.ui

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.data.MtbChoice

interface RecordChoice {

    fun <V: Parcelable,S: Parcelable>recordChoice(choice: MtbChoice<V, S>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordChoice(choices: ArrayList<MtbChoice<V, S>>, practice: Boolean)

    fun <V: Parcelable,S: Parcelable>recordNoChoice(choice: MtbChoice<V, S>, practice: Boolean)
}
