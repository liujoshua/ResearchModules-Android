/*
 * BSD 3-Clause License
 *
 * Copyright 2020  Sage Bionetworks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1.  Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2.  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3.  Neither the name of the copyright holder(s) nor the names of any contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission. No license is granted to the trademarks of
 * the copyright holders even if such marks are included in this software.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.northwestern.mobiletoolbox.flanker_app

import com.google.common.collect.ImmutableList
import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.form.FlankerChoice
import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.form.FlankerChoiceInputField
import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.flanker.FlankerFormStep
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.sagebionetworks.research.domain.task.Task
import org.slf4j.LoggerFactory
import java.io.IOException

class FlankerTaskDeserializeTest : JsonDeserializationTestBase() {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(FlankerTaskDeserializeTest::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun testDeserializeFlankerTask() {
        val json = getClasspathResourceAsString("task/flanker_all.json")
        val task = gson.fromJson(
                json, Task::class.java)
        LOGGER.debug("Task ToString: $task")

        val stepMap = task.steps.map { it.identifier to it }.toMap()

        (stepMap["A01"] as FlankerFormStep).run {
            LOGGER.debug("Step A01: $this")
            assertEquals(3, inputFields.count())
            assertEquals("firstLiveStep", stepName)
            assertEquals("setA", stepGroup)

            val inputFieldMap = inputFields.map { it.getIdentifier() to it }.toMap()

            (inputFieldMap["A01a"] as FlankerChoiceInputField).run {
                assertEquals("interimStar", getFlankerType())
                assertEquals(1250, getDelayToNextInputField())
                assertEquals(ImmutableList.of("", "", "⭐️", "", ""), getFlankers())
                assertTrue(getText()!!.startsWith("[yellow star"))

                (getChoices()[0] as FlankerChoice<*>).run {
                    assertEquals("B3C6D4A2-CB57-E3AA-874A-F371871D523D", itemResponseOID)
                    assertEquals("[arrow left]", text)
                    assertEquals(1.0, answerValue)
                }
            }
        }
    }
}