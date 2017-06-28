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
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "FietsenStallingen")
class SafeStorageDto {

    var dateTime: String? = null
        @Element(name = "DateTime") set
        @Element(name = "DateTime") get
    var bikeSheds: List<BikeShedDto>? = null
        @ElementList(name = "FietsenStalling", inline = true, type = BikeShedDto::class) set
        @ElementList(name = "FietsenStalling", inline = true, type = BikeShedDto::class) get
}
