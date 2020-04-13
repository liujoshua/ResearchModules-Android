/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.sage_extension.ResultData
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
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

    private val identifier: String
    var startDate: Instant
    var endDate: Instant?
    var fileName: String = archiveDataFileName
    override val type: ResultType

    init {
        this.identifier = result.identifier
        this.startDate = result.startTime
        this.endDate = result.endTime
        this.type = result.type
    }

    override fun getIdentifier(): String {
        return this.identifier
    }

    override fun toBuilder(): Builder {
        TODO("Not yet implemented")
    }

    override fun getEndTime(): Instant? {
        return this.endTime
    }

    override fun getStartTime(): Instant {
        return this.startTime
    }
}
