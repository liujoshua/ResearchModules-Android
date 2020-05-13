/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mtb_mobile_kit.data


class StepHelper(var steps: ArrayList<MtbStep>) {
    var stepNameMapping: MutableMap<String, MtbStep>
    var stepIndexMapping: MutableMap<String, MtbStep>

    init {
        val dictionaryName: MutableMap<String, MtbStep> = mutableMapOf()
        val dictionaryIndex: MutableMap<String, MtbStep> = mutableMapOf()
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
