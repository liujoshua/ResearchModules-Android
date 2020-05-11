/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.sage_extension.ResultData
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.result.ResultType
import org.threeten.bp.Instant

@Parcelize
data class TaskResult<StepResultType : Parcelable>(
        val taskName: String,
        val archiveDataFileName: String = "taskData.json",
        val steps: List<StepResultType>,
        val result: ResultData,
        var rawScore: Int?,
        var locale: String? = "en-US"
        )
    : ResultData(), Parcelable {

    @IgnoredOnParcel
    var fileName: String = archiveDataFileName
    @IgnoredOnParcel
    override val type: ResultType = result.type

    override fun getIdentifier(): String {
        return result.identifier
    }

    override fun toBuilder(): Builder {
        TODO("Not yet implemented")
    }

    override fun getEndTime(): Instant? {
        return result.endTime
    }

    override fun getStartTime(): Instant {
        return  result.startTime
    }
}
