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

package nl.jozuasijsling.veiligstallen.service.dto

import org.simpleframework.xml.Element

class OpeningHoursDto(

        @param:Element(name = "Ma")
        @get:Element(name = "Ma")
        val monday: Day,

        @param:Element(name = "Di")
        @get:Element(name = "Di")
        val tuesday: Day,

        @param:Element(name = "Wo")
        @get:Element(name = "Wo")
        val wednesday: Day,

        @param:Element(name = "Do")
        @get:Element(name = "Do")
        val thursday: Day,

        @param:Element(name = "Vr")
        @get:Element(name = "Vr")
        val friday: Day,

        @param:Element(name = "Za")
        @get:Element(name = "Za")
        val saturday: Day,

        @param:Element(name = "Zo")
        @get:Element(name = "Zo")
        val sunday: Day

) {
    class Day(

            @param:Element(name = "Open", required = false)
            @get:Element(name = "Open", required = false)
            val open: String?,

            @param:Element(name = "Dicht", required = false)
            @get:Element(name = "Dicht", required = false)
            val closed: String?,

            @param:Element(name = "Opmerking", required = false)
            @get:Element(name = "Opmerking", required = false)
            val remark: String?
    )
}
