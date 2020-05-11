/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.view.MotionEvent
import java.util.Date

data class UserInteraction(
        var controlEvent: MotionEvent?,
        var identifier: String?,
        var timestamp: Date?) : Parcelable {

        //TODO MotionEvent may change
        constructor(parcel: Parcel) : this(
                parcel.readParcelable(MotionEvent::class.java.classLoader),
                parcel.readString(),
                parcel.readSerializable() as? Date) {
        }

        override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(controlEventParser())
        parcel.writeString(identifier)
        parcel.writeSerializable(timestamp)
    }

    override fun describeContents() = 0

    private fun controlEventParser() = when (controlEvent?.action) {
        MotionEvent.ACTION_DOWN -> {
            "touchDown"
            //touchDownRepeat is custom also
            // touchDownRepeat -> eventString = "touchDownRepeat"
        }
        MotionEvent.ACTION_UP -> {
            "touchUpInside"
            // android does not have touchUpOutside touchUpInside/ you can get x and y and calculate Inside, Outside
            // touchUpOutside -> eventString = "touchUpOutside"
        }
        else -> "unknownEvent"
    }

        companion object CREATOR : Creator<UserInteraction> {
                override fun createFromParcel(parcel: Parcel): UserInteraction {
                        return UserInteraction(parcel)
                }

                override fun newArray(size: Int): Array<UserInteraction?> {
                        return arrayOfNulls(size)
                }
        }
}
