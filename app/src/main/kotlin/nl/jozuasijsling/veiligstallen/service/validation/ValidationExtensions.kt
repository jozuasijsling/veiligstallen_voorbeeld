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

package nl.jozuasijsling.veiligstallen.service.validation

import nl.jozuasijsling.veiligstallen.service.dto.BikeShedDto
import nl.jozuasijsling.veiligstallen.service.dto.OpeningHoursDto
import nl.jozuasijsling.veiligstallen.service.dto.SectionDto
import nl.jozuasijsling.veiligstallen.service.dto.response.SafeStorageDto


fun SafeStorageDto.validate(): Boolean {
    return dateTime?.isNotEmpty() ?: false
            && bikeSheds?.all { it.validate() } ?: false
}

fun BikeShedDto.validate(): Boolean {
    return name?.isNotEmpty() ?: false
            && description?.isNotEmpty() ?: true
            && id?.isNotEmpty() ?: false
            && municipality?.isNotEmpty() ?: false
            && street?.isNotEmpty() ?: true
            && postcode?.isNotEmpty() ?: true
            && city?.isNotEmpty() ?: true
            && url?.isNotEmpty() ?: false
            && capacityTotal?.isNotEmpty() ?: false
            && (capacityTotal!! == "Onbekend" || (capacityTotal?.toIntOrNull() != null
            && bikeCapacity != null && capacityTotal!!.toInt() >= bikeCapacity!!))
            && sections?.all { it.validate() } ?: true
            && referral != null && referral!!.isNotEmpty() && referral!!.size <= 2
            && referral!!.all(String::isNotEmpty)
            && openingHours?.validate() ?: false
            && isStationShed != null && isStationShed!! in listOf("Ja", "Nee")
            && facilities?.isNotEmpty() ?: true
            && (type == null || type!! in listOf("bewaakt", "geautomatiseerd", "onbewaakt", "buurtstalling", "fietskluizen", "toezicht", "fietstrommel"))
            && (coordinates == null || coordinates!!.size == 2)
            && access == null || access!!.trim() in listOf("Selfservice", "Deels selfservice")
            && administrator?.isNotEmpty() ?: true
            && administratorContact?.isNotEmpty() ?: true
}

private fun SectionDto.validate(): Boolean {
    return id?.isNotEmpty() ?: false
            && name?.isNotEmpty() ?: false
            && capacity != null
            && unoccupied != null && unoccupied!! <= capacity!!
            && occupied != null && occupied!! <= capacity!!
            && unoccupied!! + occupied!! == capacity
}

private fun OpeningHoursDto.validate(): Boolean {
    return monday?.validate() ?: false
            && tuesday?.validate() ?: false
            && wednesday?.validate() ?: false
            && thursday?.validate() ?: false
            && friday?.validate() ?: false
            && saturday?.validate() ?: false
            && sunday?.validate() ?: false
}

private fun OpeningHoursDto.Day.validate(): Boolean {
    if (remark != null) {
        return remark!!.isNotEmpty()
    } else {
        return open?.isNotEmpty() ?: false
                && closed?.isNotEmpty() ?: false
    }
}
