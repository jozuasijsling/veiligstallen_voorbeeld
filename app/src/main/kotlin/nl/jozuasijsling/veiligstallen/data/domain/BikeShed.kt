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

package nl.jozuasijsling.veiligstallen.data.domain

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
                    val type: BikeShedType,
                    val access: String?,
                    val administrator: String?,
                    val administratorContact: String?)

enum class BikeShedType {

    /**
     * Indoor and supervised (needs confirmation)
     */
    GUARDED,

    /**
     * Open area but supervised (needs confirmation)
     */
    SUPERVISED,

    /**
     * Place to store your bike, open access and no supervision.
     */
    UNGUARDED,

    SAFE_DEPOSIT,
    AUTOMATED,

    /**
     * Government sponsored civilian initiative. In most cases, this will be a house remodeled
     * to fit bikes that are supervised by private participants / employees.
     */
    CIVILIAN,

    /**
     * Dutch "fietstrommel" lets households without space for their bike, rent a slot in a
     * small key-locked shed.
     */
    PERSONAL_DRUM,


    /**
     * Possible error in the input, or unsupported type. Any unsupported type should
     * be detected by and reported, so we can update the model and add support for it.
     */
    UNKNOWN;
}

fun localiseBikeShedType(typeText: String?) = when (typeText) {
    "bewaakt" -> BikeShedType.GUARDED
    "fietskluizen" -> BikeShedType.SAFE_DEPOSIT
    "geautomatiseerd" -> BikeShedType.AUTOMATED
    "onbewaakt" -> BikeShedType.UNGUARDED
    "buurtstalling" -> BikeShedType.CIVILIAN
    "fietstrommel" -> BikeShedType.PERSONAL_DRUM
    "toezicht" -> BikeShedType.SUPERVISED
    else -> BikeShedType.UNKNOWN
}