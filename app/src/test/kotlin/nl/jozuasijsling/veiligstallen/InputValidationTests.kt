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

package nl.jozuasijsling.veiligstallen

import com.google.common.truth.Truth.assertThat
import nl.jozuasijsling.veiligstallen.service.dto.response.SafeStorageDto
import nl.jozuasijsling.veiligstallen.service.validation.validate
import org.junit.Test
import org.simpleframework.xml.core.PersistenceException
import org.simpleframework.xml.core.Persister
import org.simpleframework.xml.core.ValueRequiredException

class InputValidationTests {

    @Test
    fun testFilePassesValidation() {
        val serializer = Persister()
        val xml = InputValidationTests::class.java
                .getResourceAsStream("veiligstallen.xml")
                .bufferedReader()
                .use { it.readText() }

        val dto = serializer.read(SafeStorageDto::class.java, xml)

        assertThat(dto.validate()).isTrue()
    }

    @Test(expected = PersistenceException::class)
    fun emptyFileFailsValidation() {
        val xml = """<?xml version="1.0" encoding="UTF-8"?><Fietsenstallingen/>"""
        val serializer = Persister()

        serializer.read(SafeStorageDto::class.java, xml)
    }

}
