/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.InterruptionTimestampEnum
import kotlinx.android.parcel.Parcelize
import java.util.Calendar

@Parcelize
data class InterruptionManager(
        private var interruptions: ArrayList<Interruption> = ArrayList(),
        var delegate: InterruptionManagerDelegate
) : Parcelable {

    fun register(currentViewController: InterruptionManagerDelegate) {
        delegate = currentViewController
    }

    fun listOfInterruptions(): ArrayList<Interruption> {
        return interruptions.clone() as? ArrayList<Interruption> ?: ArrayList()
    }

    fun announceInterruption() {
        interruptions.add(Interruption(InterruptionTimestampEnum.interruptionTime, Calendar.getInstance()))
        //***Save data here
        delegate.didBeginInterruption()
    }

    fun resumeFromInterruption() {
        interruptions.add(Interruption(InterruptionTimestampEnum.resumeTime, Calendar.getInstance()))
        delegate.didEndInterruption()
    }

    fun appTerminating() {
        interruptions.add(Interruption(InterruptionTimestampEnum.terminationTime, Calendar.getInstance()))
        //***TODO - Send saved data to Bridge
    }
}


