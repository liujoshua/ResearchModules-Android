/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

interface InterruptionManagerDelegate: Parcelable {
    fun didBeginInterruption()
    fun didEndInterruption()
}