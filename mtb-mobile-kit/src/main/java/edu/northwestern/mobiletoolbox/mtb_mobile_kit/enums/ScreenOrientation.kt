/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.enums

import android.content.pm.ActivityInfo

enum class ScreenOrientation(val orientation: Int) {
    portrait(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT),
    portraitUpsideDown(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT),
    landscapeLeft(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
    landscapeRight(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE),
    landscape(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
}
