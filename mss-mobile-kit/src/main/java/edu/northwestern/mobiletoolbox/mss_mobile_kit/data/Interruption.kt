/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.InterruptionTimestampEnum
import kotlinx.android.parcel.Parcelize
import java.util.Calendar

@Parcelize
data class Interruption(
        val name: InterruptionTimestampEnum,
        val timestamp: Calendar
): Parcelable