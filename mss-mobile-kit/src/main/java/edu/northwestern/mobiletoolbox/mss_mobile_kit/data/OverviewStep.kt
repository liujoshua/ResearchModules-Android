/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.sage_extension.IconInfo
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.step.implementations.UIStepBase

@Parcelize
data class OverviewStep(
        private var icons: ArrayList<IconInfo>,
        override var stepName: String?,
        override var htmlText: String?,
        var timeout: Int?
): UIStepBase(), Parcelable, MssStep
