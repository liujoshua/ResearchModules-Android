/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.timer

import java.util.Date

interface TimeManager{
    fun startResponse()

    fun getResponseTime(): Double
}
