/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import org.sagebionetworks.research.domain.step.interfaces.Step

/// Protocol for MSS steps that use stepName in JSON file for navigation and UI identification
interface Step: Step {
    var stepName: String?
    //Alternative text for attributed strings
    var htmlText: String?
}