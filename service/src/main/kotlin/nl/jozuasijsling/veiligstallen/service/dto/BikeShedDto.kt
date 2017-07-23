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

@Root(name = "Fietsenstalling")
class BikeShedDto(

        @param:Element(name = "Naam", data = true)
        @get:Element(name = "Naam", data = true)
        val name: String,

        @param:Element(name = "ID")
        @get:Element(name = "ID")
        val id: String,

        @param:Element(name = "Url")
        @get:Element(name = "Url")
        val url: String,

        @param:Element(name = "Gemeente", data = true)
        @get:Element(name = "Gemeente", data = true)
        val municipality: String,

        @param:Element(name = "CapaciteitTotaal")
        @get:Element(name = "CapaciteitTotaal")
        val capacityTotal: String,

        @param:Element(name = "Stationsstalling")
        @get:Element(name = "Stationsstalling")
        val isStationShed: String?,

        @param:Element(name = "Omschrijving", required = false)
        @get:Element(name = "Omschrijving", required = false)
        val description: String?,

        @param:Element(name = "Straat", required = false, data = true)
        @get:Element(name = "Straat", required = false, data = true)
        val street: String?,

        @param:Element(name = "Postcode", required = false, data = true)
        @get:Element(name = "Postcode", required = false, data = true)
        val postcode: String?,

        @param:Element(name = "Plaats", required = false, data = true)
        @get:Element(name = "Plaats", required = false, data = true)
        val city: String?,

        @param:Element(name = "CapaciteitFiets", required = false)
        @get:Element(name = "CapaciteitFiets", required = false)
        val bikeCapacity: Int?,

        @param:Element(name = "CapaciteitEFiets", required = false)
        @get:Element(name = "CapaciteitEFiets", required = false)
        val eBikeCapacity: Int?,

        @param:Element(name = "CapaciteitBromfiets", required = false)
        @get:Element(name = "CapaciteitBromfiets", required = false)
        val mopedCapacity: Int?,

        @param:Element(name = "CapaciteitMotorfiets", required = false)
        @get:Element(name = "CapaciteitMotorfiets", required = false)
        val motorBikeCapacity: Int?,

        @param:Element(name = "CapaciteitSpecialeFiets", required = false)
        @get:Element(name = "CapaciteitSpecialeFiets", required = false)
        val specialBikeCapacity: Int?,

        @param:Element(name = "Openingstijden", required = false)
        @get:Element(name = "Openingstijden", required = false)
        val openingHours: OpeningHoursDto?,

        @param:Element(name = "Voorzieningen", required = false, data = true)
        @get:Element(name = "Voorzieningen", required = false, data = true)
        val facilities: String?,

        @param:Element(name = "Type", required = false)
        @get:Element(name = "Type", required = false)
        val type: String?,

        @param:Element(name = "Coordinaten", required = false)
        @get:Element(name = "Coordinaten", required = false)
        val coordinates: DoubleArray?,

        @param:Element(name = "Toegangscontrole", required = false)
        @get:Element(name = "Toegangscontrole", required = false)
        val access: String?,

        @param:Element(name = "Beheerder", required = false, data = true)
        @get:Element(name = "Beheerder", required = false, data = true)
        val administrator: String?,

        @param:Element(name = "BeheerderContact", required = false, data = true)
        @get:Element(name = "BeheerderContact", required = false, data = true)
        val administratorContact: String?,

        @param:Element(name = "Abonnementen", required = false)
        @get:Element(name = "Abonnementen", required = false)
        val subscriptions: SubscriptionsDto?,

        @param:Element(name = "Verwijssysteem", required = false)
        @get:Element(name = "Verwijssysteem", required = false)
        val referral: String?,

        @param:ElementList(name = "Secties", required = false)
        @get:ElementList(name = "Secties", required = false)
        val sections: List<SectionDto>?
)
