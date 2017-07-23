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

package nl.jozuasijsling.veiligstallen.data

import nl.jozuasijsling.veiligstallen.data.domain.*
import nl.jozuasijsling.veiligstallen.service.dto.BikeShedDto
import nl.jozuasijsling.veiligstallen.service.dto.OpeningHoursDto
import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import nl.jozuasijsling.veiligstallen.service.dto.SectionDto
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


private val timestampFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

fun SafeStorageDto.toDomainObject() =
        SafeStorage(DateTime.parse(dateTime, timestampFormat),
                bikeSheds.map { it.toDomainObject() })


fun BikeShedDto.toDomainObject(): BikeShed {
    return BikeShed(name,
            description ?: "Unknown",
            id,
            extractAddress(),
            extractCoordinates(),
            url,
            bikeCapacity ?: 0,
            capacityTotal,
            referral,
            sections?.map { it.toDomainObject() } ?: emptyList(),
            openingHours?.toDomainObject(),
            isStationShed == "Ja",
            facilities?.split(",") ?: emptyList(),
            localiseBikeShedType(type),
            access,
            administrator,
            administratorContact)
}

fun OpeningHoursDto.toDomainObject() =
        OpeningHours(monday.describe(),
                tuesday.describe(), wednesday.describe(),
                thursday.describe(), friday.describe(),
                saturday.describe(), sunday.describe())


private fun OpeningHoursDto.Day.describe() =
        remark ?: "${open!!} - ${closed!!}"


private fun SectionDto.toDomainObject() =
        InnerSection(id, name, capacity, available, occupied)


private fun BikeShedDto.extractCoordinates() =
        coordinates?.let { GeoLocation(it[1], it[0]) }


private fun BikeShedDto.extractAddress() =
        Address(municipality, street ?: "", postcode ?: "", city ?: "")
