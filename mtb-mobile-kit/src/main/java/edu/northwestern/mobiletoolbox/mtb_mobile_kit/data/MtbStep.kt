/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import com.google.common.collect.ImmutableSet
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step

/// Protocol for MSS steps that use stepName in JSON file for navigation and UI identification
interface MtbStep : Step {

    var stepName: String?
    //Alternative text for attributed strings
    var htmlText: String?

    override fun getIdentifier(): String {
        TODO("Not yet implemented")
    }

    override fun getType(): String {
        TODO("Not yet implemented")
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        TODO("Not yet implemented")
    }

    override fun copyWithIdentifier(identifier: String): Step {
        TODO("Not yet implemented")
    }
}
