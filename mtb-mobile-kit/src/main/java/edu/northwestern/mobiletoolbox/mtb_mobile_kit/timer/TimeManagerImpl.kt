/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.timer

class TimeManagerImpl : TimeManager {

    override val presentationTimer =  TimerImpl()

    override var enableUIDelay: Long = 0

    override fun startResponse() {
        TODO("Not yet implemented")
    }

    override fun getResponseTime(): Double {
        TODO("Not yet implemented")
    }
}

