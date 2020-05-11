/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.timer

interface TimeManager{
    /***
     * enableUIDelay will be counted in Millis
     */
    var enableUIDelay: Long

    val presentationTimer: Timer

    fun startResponse()

    fun getResponseTime(): Double
}
