/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable
import java.util.Date

data class StepResult<ResponseType : Parcelable, ScoreType : Parcelable>(
        var identifier: String,

        ///Administration order of step
        var position: Int? = null,

        ///date when the step administration started
        var startDate: Date? = null,

        ///date when the step administration ended
        var endDate: Date? = null,

        ///flag to specify if the administered step is a practice step
        var practice: Boolean = false,

        ///time in seconds, between presentation of step and user response
        var responseTime: Double? = 0.0,

        //user interactions that get added after the result is created
        var interactions: ArrayList<UserInteraction>? = null,

        ///participant response for a step
        var response: ResponseType? = null,

        ///score of a participant response
        var score: ScoreType? = null
)
