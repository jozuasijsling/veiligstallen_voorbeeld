/*
 * Copyright (c) 2017 Jozua Sijsling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.jozuasijsling.veiligstallen.service

import com.google.common.truth.Truth.assertThat
import nl.jozuasijsling.veiligstallen.service.validation.validate
import nl.jozuasijsling.veiligstallen.test.deserializeFromResource
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class InputValidationTests(date: String) {

    private val suffix = date.replace("-", "")
    private val filename = "resources/veiligstallen_$suffix.xml"

    @Test
    fun testFilePassesValidation() {

        val dto = deserializeFromResource(filename)

        assertThat(dto.validate()).isTrue()
    }

    companion object {
        @JvmStatic @Parameters(name = "File dated {0} passes validation")
        fun data(): Iterable<Array<String>> {
            return arrayOf("2017-06-30", "2017-07-23", "2017-07-29")
                    .map { arrayOf(it) }
        }
    }
}
