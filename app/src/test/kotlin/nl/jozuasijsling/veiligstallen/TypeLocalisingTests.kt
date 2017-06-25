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
import nl.jozuasijsling.veiligstallen.data.domain.BikeShedType
import nl.jozuasijsling.veiligstallen.data.domain.localiseBikeShedType
import nl.jozuasijsling.veiligstallen.data.toDomainObject
import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.simpleframework.xml.core.Persister
import java.util.*

@RunWith(value = Parameterized::class)
class TypeLocalisingTests(date: String) {

    companion object {
        @JvmStatic
        @Parameters(name = "File dated {0} has no unexpected unknowns")
        fun data(): Iterable<Array<String>> {
            return Arrays.asList(arrayOf("2017-03-25"), arrayOf("2017-06-08"))
        }
    }

    private val suffix = date.replace("-", "")

    @Test
    fun testFileHasNoBikeShedsWithUnknownType() {
        val serializer = Persister()
        val xml = TypeLocalisingTests::class.java
                .getResourceAsStream("veiligstallen_$suffix.xml")
                .bufferedReader()
                .use { it.readText() }
        val dto = serializer.read(SafeStorageDto::class.java, xml)
        val parsedObject = dto.toDomainObject()


        val unknownTypes = parsedObject.bikeSheds
                .map { Pair(it.type, localiseBikeShedType(it.type)) }
                .filter { it.second == BikeShedType.UNKNOWN }
                .filter { it.first != null }

        assertThat(unknownTypes).isEmpty()
    }

}
