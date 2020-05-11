/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcelable
import com.google.common.collect.ImmutableSet
import edu.northwestern.mobiletoolbox.mtb_mobile_kit.sage_extension.IconInfo
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.implementations.UIStepBase

@Parcelize
data class OverviewStep(
        private var icons: ArrayList<IconInfo>,
        override var stepName: String?,
        override var htmlText: String?,
        var timeout: Int?
): UIStepBase(), Parcelable, MtbStep{

    override fun getIdentifier(): String {
        return super<MtbStep>.getIdentifier()
    }

    override fun getType(): String {
        return super<MtbStep>.getType()
    }

    override fun copyWithIdentifier(identifier: String): UIStepBase {
        TODO("Not yet implemented")
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return super<MtbStep>.getAsyncActions()
    }
}
