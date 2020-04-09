/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.sage_extension.ResultData
import org.sagebionetworks.research.domain.result.ResultType
import org.threeten.bp.Instant
import java.util.Date

open class TaskResult<StepResultType: Parcelable>(task: String, archiveDataFileName: String,
        steps: List<StepResultType>, result: ResultData, override val type: ResultType, var rawScore: Int?)
    : ResultData() {
    private val identifier: String
    var startDate: Instant
    var endDate: Instant?
    val taskName: String = task
    var steps: List<StepResultType>? = steps
    var locale: String? = "en-US"
    var fileName: String = archiveDataFileName

    init {
        this.identifier = result.identifier
        this.startDate = result.startTime
        this.endDate = result.endTime
    }
    override fun getIdentifier(): String {
        return identifier
    }

    override fun toBuilder(): Builder {
        TODO("Not yet implemented")
    }

    override fun getEndTime(): Instant? {
        return endTime
    }

    override fun getStartTime(): Instant {
        return startTime
    }
}
