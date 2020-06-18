/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.result.interfaces.Result
import java.util.UUID
import org.sagebionetworks.research.domain.form.interfaces.Choice

@Parcelize
data class MtbChoice<ValueType : Parcelable, ScoreType : Parcelable>(
        val value: ValueType,
        val score: ScoreType,
        val itemResponseOID: UUID,
        val dataType: String?,
        val image: String?,
        private val iconName: String?,
        private val text: String,
        private val detail: String?
) : Parcelable, Choice<ValueType> {

    override fun getIconName(): String? {
        return iconName
    }

    override fun isExclusive(): Boolean {
        return isExclusive
    }

    override fun getText(): String {
        return text
    }

    //TODO implement in the feature
    override fun isEqualToResult(result: Result?): Boolean {
        return false
    }

    override fun getAnswerValue(): ValueType {
        return value
    }

    override fun getDetail(): String? {
        return detail
    }
}