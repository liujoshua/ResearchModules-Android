/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import com.google.common.collect.ImmutableSet
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step

data class FormUIStep(
        var isPractice: Boolean = false,
        var timeout: Int? = null,
        override var stepName: String?,
        override var htmlText: String?,
        private val identifier: String,
        private val type: String,
        private val asyncActions: ImmutableSet<AsyncActionConfiguration>
) : MssStep {

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

    override fun copyWithIdentifier(identifier: String): Step {
        return copy(identifier = identifier)
    }
}

