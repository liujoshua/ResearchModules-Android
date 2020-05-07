/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.timer

import java.util.Date

interface TimeManager{
    /***
     * enableUIDelay will be counted in Millis
     */
    var enableUIDelay: Long

    val presentationTimer: Timer

    fun startResponse()

    fun getResponseTime(): Double
}
