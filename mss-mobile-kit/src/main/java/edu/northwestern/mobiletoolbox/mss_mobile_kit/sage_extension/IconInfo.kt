/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.sage_extension

import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IconInfo(
        val title: String,
        val subtitle: String,
        val icon: String//
) : Parcelable