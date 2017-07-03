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
import org.simpleframework.xml.Root

@Root(name = "Sectie")
class SectionDto(

        @param:Element(name = "ID")
        @get:Element(name = "ID")
        val id: String,

        @param:Element(name = "Naam", data = true)
        @get:Element(name = "Naam", data = true)
        val name: String,

        @param:Element(name = "Capaciteit")
        @get:Element(name = "Capaciteit")
        val capacity: Int,

        @param:Element(name = "Vrij")
        @get:Element(name = "Vrij")
        val available: Int,

        @param:Element(name = "Bezet")
        @get:Element(name = "Bezet")
        val occupied: Int
)