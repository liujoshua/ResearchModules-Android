/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MtbAnimationTiming(
        val animationDuration: Long?,
        /// Amount of time that the object should remain on the screen
        val presentationDuration: Long?
) : Parcelable
