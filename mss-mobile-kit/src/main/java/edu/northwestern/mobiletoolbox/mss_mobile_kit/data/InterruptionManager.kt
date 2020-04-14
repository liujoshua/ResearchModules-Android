/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.InterruptionTimestamp
import kotlinx.android.parcel.Parcelize

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
        interruptions.add(Interruption(InterruptionTimestamp.interruptionTime, System.nanoTime()))
        //***Save data here
        delegate.didBeginInterruption()
    }

    fun resumeFromInterruption() {
        interruptions.add(Interruption(InterruptionTimestamp.resumeTime, System.nanoTime()))
        delegate.didEndInterruption()
    }

    fun appTerminating() {
        interruptions.add(Interruption(InterruptionTimestamp.terminationTime, System.nanoTime()))
        //***TODO - Send saved data to Bridge
    }
}


