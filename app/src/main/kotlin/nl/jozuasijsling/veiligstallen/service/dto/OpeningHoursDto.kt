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

class OpeningHoursDto {

    var monday: Day? = null
        @Element(name = "Ma") set
        @Element(name = "Ma") get
    var tuesday: Day? = null
        @Element(name = "Di") set
        @Element(name = "Di") get
    var wednesday: Day? = null
        @Element(name = "Wo") set
        @Element(name = "Wo") get
    var thursday: Day? = null
        @Element(name = "Do") set
        @Element(name = "Do") get
    var friday: Day? = null
        @Element(name = "Vr") set
        @Element(name = "Vr") get
    var saturday: Day? = null
        @Element(name = "Za") set
        @Element(name = "Za") get
    var sunday: Day? = null
        @Element(name = "Zo") set
        @Element(name = "Zo") get

    class Day {
        var open: String? = null
            @Element(name = "Open", required = false) set
            @Element(name = "Open", required = false) get
        var closed: String? = null
            @Element(name = "Dicht", required = false) set
            @Element(name = "Dicht", required = false) get
        var remark: String? = null
            @Element(name = "Opmerking", required = false) set
            @Element(name = "Opmerking", required = false)  get
    }
}
