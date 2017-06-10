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

import android.support.annotation.Keep
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Keep
@Root(name = "Sectie")
class SectionDto {

    var id: String? = null
        @Element(name = "ID") set
        @Element(name = "ID") get
    var name: String? = null
        @Element(name = "Naam") set
        @Element(name = "Naam") get
    var capacity: Int? = null
        @Element(name = "Capaciteit") set
        @Element(name = "Capaciteit") get
    var unoccupied: Int? = null
        @Element(name = "Vrij") set
        @Element(name = "Vrij") get
    var occupied: Int? = null
        @Element(name = "Bezet") set
        @Element(name = "Bezet") get
}
