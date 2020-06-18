/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable

interface InterruptionManagerDelegate: Parcelable {
    fun didBeginInterruption()
    fun didEndInterruption()
}