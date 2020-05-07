/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data


class StepHelper(var steps: ArrayList<MssStep>) {
    var stepNameMapping: MutableMap<String, MssStep>
    var stepIndexMapping: MutableMap<String, MssStep>

    init {
        val dictionaryName: MutableMap<String, MssStep> = mutableMapOf()
        val dictionaryIndex: MutableMap<String, MssStep> = mutableMapOf()
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
