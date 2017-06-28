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

fun SafeStorageDto.toDomainObject(): SafeStorage {
    return SafeStorage(DateTime.parse(dateTime, timestampFormat),
            bikeSheds!!.map { it.toDomainObject() })
}

fun BikeShedDto.toDomainObject(): BikeShed {
    return BikeShed(name!!,
            description ?: "Unknown",
            id!!,
            extractAddress(),
            extractCoordinates(),
            url!!,
            bikeCapacity ?: 0,
            capacityTotal!!,
            referral!!.first().let { Integer.parseInt(it) },
            if (referral!!.size == 1) {
                null
            } else {
                referral!![1]
            },
            sections?.map { it.toDomainObject() } ?: emptyList(),
            openingHours!!.toDomainObject(),
            isStationShed == "Ja",
            facilities?.split(",") ?: emptyList(),
            localiseBikeShedType(type),
            access,
            administrator,
            administratorContact)
}

fun OpeningHoursDto.toDomainObject(): OpeningHours {
    return OpeningHours(monday!!.describe(),
            tuesday!!.describe(),
            wednesday!!.describe(),
            thursday!!.describe(),
            friday!!.describe(),
            saturday!!.describe(),
            sunday!!.describe())
}

fun OpeningHoursDto.Day.describe(): String {
    if (remark != null) {
        return remark!!
    } else {
        return "${open!!} - ${closed!!}"
    }
}

fun SectionDto.toDomainObject(): InnerSection {
    return InnerSection(id!!, name!!, capacity!!, unoccupied!!, occupied!!)
}

fun BikeShedDto.extractCoordinates(): GeoLocation? {
    val coordinatesVal = coordinates
    if (coordinatesVal == null) {
        return null
    } else {
        return GeoLocation(coordinatesVal[1], coordinatesVal[0])
    }
}

fun BikeShedDto.extractAddress(): Address? {
    if (municipality == null && street == null && postcode == null && city == null) {
        return null
    } else {
        return Address(municipality ?: "", street ?: "", postcode ?: "", city ?: "")
    }
}
