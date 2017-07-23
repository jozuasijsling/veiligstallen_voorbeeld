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
import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import nl.jozuasijsling.veiligstallen.service.dto.SectionDto
import okhttp3.HttpUrl


fun SafeStorageDto.validate(): Boolean {
    return dateTime.isNotEmpty()
            && bikeSheds.all { it.validate() }
}

fun BikeShedDto.validate(): Boolean {
    return name.isNotEmpty()
            && description?.isNotEmpty() ?: true
            && id.isNotEmpty()
            && municipality.isNotEmpty()
            && street?.isNotEmpty() ?: true
            && postcode?.isNotEmpty() ?: true
            && city?.isNotEmpty() ?: true
            && HttpUrl.parse(url) != null
            && capacityTotal.isNotEmpty()
            && (capacityTotal == "Onbekend" || capacityTotal.toIntOrNull() != null)
            && sections?.all { it.validate() } ?: true
            && (referral == null || validateInlinedReferralPhrases())
            && openingHours?.validate() ?: true
            && isStationShed != null && isStationShed in listOf("Ja", "Nee")
            && facilities?.isNotEmpty() ?: true
            && (type == null || type in listOf("bewaakt", "geautomatiseerd", "onbewaakt", "buurtstalling", "fietskluizen", "toezicht", "fietstrommel"))
            && (coordinates == null || coordinates.size == 2)
            && (access == null || access.trim() in listOf("Selfservice", "Deels selfservice"))
            && administrator?.isNotEmpty() ?: true
            && administratorContact?.isNotEmpty() ?: true
}

private fun BikeShedDto.validateInlinedReferralPhrases(): Boolean {
    return HttpUrl.parse(referral) != null
}

private fun SectionDto.validate(): Boolean {
    return id.isNotEmpty() && name.isNotEmpty()
    // Unfortunately, some of the data does not add up
//            && available <= capacity
//            && occupied <= capacity
//            && available + occupied == capacity
}

private fun OpeningHoursDto.validate(): Boolean {
    return monday.validate()
            && tuesday.validate()
            && wednesday.validate()
            && thursday.validate()
            && friday.validate()
            && saturday.validate()
            && sunday.validate()
}

private fun OpeningHoursDto.Day.validate(): Boolean {
    if (remark != null) {
        return remark.isNotEmpty()
    } else {
        return open?.isNotEmpty() ?: false
                && closed?.isNotEmpty() ?: false
    }
}