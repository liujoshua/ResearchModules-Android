/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import java.util.Date

interface StepResult {
    var identifier: String

    ///Administration order of step
    var position: Int?

    ///date when the step administration started
    var startDate: Date?

    ///date when the step administration ended
    var endDate: Date?

    ///flag to specify if the administered step is a practice step
    var practice: Boolean

    ///time in seconds, between presentation of step and user response
    var responseTime: Double?

    //user interactions that get added after the result is created
    var interactions: ArrayList<UserInteraction>?
}