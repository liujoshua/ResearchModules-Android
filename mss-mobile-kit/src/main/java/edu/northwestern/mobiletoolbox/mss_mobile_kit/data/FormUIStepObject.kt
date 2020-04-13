/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import com.google.common.collect.ImmutableSet
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration

data class FormUIStepObject(

        var isPractice: Boolean = false,
        var timeout: Int? = null,
        override var stepName: String?,
        override var htmlText: String?
) : Step {

//TODO also should be extended FormUIStep or UIStep

    override fun getIdentifier(): String {
        return identifier
    }

    override fun getType(): String {
        return type
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return asyncActions
    }

    override fun copyWithIdentifier(identifier: String): org.sagebionetworks.research.domain.step.interfaces.Step {
        return copyWithIdentifier(identifier)
    }
}

