/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import com.google.common.collect.ImmutableSet
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step as RSDStep

/// Protocol for MSS steps that use stepName in JSON file for navigation and UI identification
data class Step(
    var stepName: String?,
    //Alternative text for attributed strings
    var htmlText: String?
): RSDStep {

    override fun getIdentifier(): String {
        TODO("Not yet implemented")
    }

    override fun getType(): String {
        TODO("Not yet implemented")
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        TODO("Not yet implemented")
    }

    override fun copyWithIdentifier(identifier: String): org.sagebionetworks.research.domain.step.interfaces.Step {
        TODO("Not yet implemented")
    }
}
