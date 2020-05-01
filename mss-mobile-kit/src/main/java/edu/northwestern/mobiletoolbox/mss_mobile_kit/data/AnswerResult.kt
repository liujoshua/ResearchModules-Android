/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import org.sagebionetworks.research.domain.result.AnswerResultType
import org.sagebionetworks.research.domain.result.ResultType
import org.threeten.bp.Instant
import java.util.Date
import org.sagebionetworks.research.domain.result.interfaces.AnswerResult as RSDAnswerResult

data class AnswerResult<T : AnswerResultType>(
        /// The identifier associated with the task, step, or asynchronous action.
        val _identifier: String,

        /// A String that indicates the type of the result. This is used to decode the result using a `RSDFactory`.
        val type: ResultType?,

        /// The start date timestamp for the result.
        val startDate: Date = Date(),

        /// The end date timestamp for the result.
        var endDate: Date = Date(),

        /// The answer type of the answer result. This includes coding information required to encode and
        /// decode the value. The value is expected to conform to one of the coding types supported by the answer type.
        val answerType: T?,

        /// The answer for the result.
        var value: Any?,

        /// The question text for the form step (if applicable).
        var questionText: String? = null
) : RSDAnswerResult<T> {

    override fun getIdentifier(): String {
        return _identifier
    }

    override fun getAnswer(): T? {
        return answerType
    }

    override fun getAnswerResultType(): String {
        TODO("Not yet implemented")
    }

    override fun getType(): String {
        return type.toString()
    }

    override fun getEndTime(): Instant {
        TODO("Not yet implemented")
    }

    override fun getStartTime(): Instant {
        TODO("Not yet implemented")
    }
}
