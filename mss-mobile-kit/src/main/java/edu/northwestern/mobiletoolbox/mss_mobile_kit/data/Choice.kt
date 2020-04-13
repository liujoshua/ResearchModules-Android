/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.result.interfaces.Result
import org.sagebionetworks.research.presentation.DisplayDrawable
import java.util.UUID
import org.sagebionetworks.research.domain.form.interfaces.Choice as RSDChoice

@Parcelize
data class Choice<ValueType : Parcelable, ScoreType : Parcelable>(
        val value: ValueType,
        val score: ScoreType,
        val itemResponseOID: UUID,
        val dataType: String?,
        val image: String?
) : Parcelable, RSDChoice<ValueType> {

    override fun getIconName(): String? {
       return iconName
    }

    override fun isExclusive(): Boolean {
        return isExclusive
    }

    override fun getText(): String {
       return text
    }

    override fun isEqualToResult(result: Result?): Boolean {
        return isEqualToResult(result)
    }

    override fun getAnswerValue(): ValueType {
        return value
    }

    override fun getDetail(): String? {
       return detail
    }
}
