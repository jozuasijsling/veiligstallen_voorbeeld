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

package nl.jozuasijsling.veiligstallen.content

import nl.jozuasijsling.veiligstallen.content.shed.Address
import nl.jozuasijsling.veiligstallen.content.shed.GeoLocation
import nl.jozuasijsling.veiligstallen.content.shed.InnerSection
import nl.jozuasijsling.veiligstallen.content.shed.OpeningHours
import nl.jozuasijsling.veiligstallen.service.dto.BikeShedDto
import okhttp3.HttpUrl

data class BikeShed(val name: String,
                    val description: String,
                    val id: String,
                    val address: Address?,
                    val coordinates: GeoLocation?,
                    val url: HttpUrl,
                    val bikeCapacity: Int,
                    val totalCapacity: String,
                    val referralCode: Int,
                    val referralUrl: HttpUrl?,
                    val sections: List<InnerSection>,
                    val openingHours: OpeningHours,
                    val isStationShed: Boolean,
                    val facilities: List<String>,
                    val type: String?,
                    val access: String?,
                    val administrator: String?,
                    val administratorContact: String?)
