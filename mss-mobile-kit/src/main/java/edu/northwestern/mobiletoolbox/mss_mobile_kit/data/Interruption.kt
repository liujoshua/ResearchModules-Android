/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.InterruptionTimestamp
import kotlinx.android.parcel.Parcelize
import java.time.Instant

@Parcelize
data class Interruption(
        val name: InterruptionTimestamp,
        val timestamp: Long
): Parcelable