/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import edu.northwestern.mobiletoolbox.mss_mobile_kit.common.RawRepresentable

class StepHelper<StepName : RawRepresentable> {
    //where StepName ==  String
    var steps: ArrayList<Step>
    var stepMapping: MutableMap<String, Step>

    constructor(steps: ArrayList<Step>) {
        this.steps = steps
        var dictionary: MutableMap<String, Step> = mutableMapOf()
        for (index in 0 until steps.size) {
            this.steps[index].stepName?.let { stepName ->
                dictionary[stepName] = this.steps[index]
            }
        }
        this.stepMapping = dictionary
    }
}