/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.sage_extension

import org.sagebionetworks.research.domain.result.ResultType
import org.sagebionetworks.research.domain.result.data.ResultData as SageResultData

abstract class ResultData : SageResultData() {

  abstract  val type: ResultType
}