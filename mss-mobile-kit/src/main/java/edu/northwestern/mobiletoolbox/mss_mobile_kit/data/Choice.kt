/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
data class Choice<ValueType : Parcelable, ScoreType : Parcelable>(
        val text: String?,
        val value: ValueType,
        val score: ScoreType?,
        val itemResponseOID: UUID,
        val dataType: String?,
        val image: String, //TODO missing RSDImageWrapper?
        var detail: String?,
        var isExclusive: Boolean
) : Parcelable