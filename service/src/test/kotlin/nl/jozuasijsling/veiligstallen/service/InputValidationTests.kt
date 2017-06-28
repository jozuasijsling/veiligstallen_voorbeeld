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
import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import nl.jozuasijsling.veiligstallen.service.validation.validate
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.simpleframework.xml.core.Persister
import java.util.*

@RunWith(value = Parameterized::class)
class InputValidationTests(date: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "File dated {0} passes validation")
        fun data(): Iterable<Array<String>> {
            return Arrays.asList(arrayOf("2017-03-25"), arrayOf("2017-06-08"))
        }
    }

    private val suffix = date.replace("-", "")
    private val filename = "resources/veiligstallen_$suffix.xml"

    @Test
    fun testFilePassesValidation() {

        val dto = ClassLoader.getSystemResourceAsStream(filename).use {
            Persister().read(SafeStorageDto::class.java, it)
        }
        assertThat(dto.validate()).isTrue()
    }

}
