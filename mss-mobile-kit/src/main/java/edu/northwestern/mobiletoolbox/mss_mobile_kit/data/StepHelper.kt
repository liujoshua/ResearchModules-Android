/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data


class StepHelper(var steps: ArrayList<Step>) {
    var stepNameMapping: MutableMap<String, Step>
    var stepIndexMapping: MutableMap<String, Step>

    init {
        val dictionaryName: MutableMap<String, Step> = mutableMapOf()
        val dictionaryIndex: MutableMap<String, Step> = mutableMapOf()
        for (index in 0 until steps.size) {
            this.steps[index].stepName?.let { stepName ->
                dictionaryName[stepName] = this.steps[index]
            }
            dictionaryIndex[this.steps[index].identifier] = this.steps[index]

        }
        this.stepNameMapping = dictionaryName
        this.stepIndexMapping = dictionaryIndex
    }
}