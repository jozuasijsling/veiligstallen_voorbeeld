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
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Keep
@Root(name = "Fietsenstalling")
class BikeShedDto {

    var name: String? = null
        @Element(name = "Naam") set
        @Element(name = "Naam") get
    var description: String? = null
        @Element(name = "Omschrijving", required = false) set
        @Element(name = "Omschrijving", required = false) get
    var id: String? = null
        @Element(name = "ID") set
        @Element(name = "ID") get
    var municipality: String? = null
        @Element(name = "Gemeente") set
        @Element(name = "Gemeente") get
    var street: String? = null
        @Element(name = "Straat", required = false) set
        @Element(name = "Straat", required = false) get
    var postcode: String? = null
        @Element(name = "Postcode", required = false) set
        @Element(name = "Postcode", required = false) get
    var city: String? = null
        @Element(name = "Plaats", required = false) set
        @Element(name = "Plaats", required = false) get
    var url: String? = null
        @Element(name = "Url") set
        @Element(name = "Url") get
    var bikeCapacity: Int? = null
        @Element(name = "CapaciteitFiets", required = false) set
        @Element(name = "CapaciteitFiets", required = false) get
    var referral: List<String>? = null
        @ElementList(entry = "Verwijssysteem", inline = true) set
        @ElementList(entry = "Verwijssysteem", inline = true) get
    var capacityTotal: String? = null
        @Element(name = "CapaciteitTotaal") set
        @Element(name = "CapaciteitTotaal") get
    var sections: List<SectionDto>? = null
        @ElementList(name = "Secties", required = false) set
        @ElementList(name = "Secties", required = false) get
    var openingHours: OpeningHoursDto? = null
        @Element(name = "Openingstijden") set
        @Element(name = "Openingstijden") get
    var isStationShed: String? = null
        @Element(name = "Stationsstalling") set
        @Element(name = "Stationsstalling") get
    var facilities: String? = null
        @Element(name = "Voorzieningen", required = false) set
        @Element(name = "Voorzieningen", required = false) get
    var type: String? = null
        @Element(name = "Type", required = false) set
        @Element(name = "Type", required = false) get
    var coordinates: DoubleArray? = null
        @Element(name = "Coordinaten", required = false) set
        @Element(name = "Coordinaten", required = false) get
    var access: String? = null
        @Element(name = "Toegangscontrole", required = false) set
        @Element(name = "Toegangscontrole", required = false) get
    var administrator: String? = null
        @Element(name = "Beheerder", required = false) set
        @Element(name = "Beheerder", required = false) get
    var administratorContact: String? = null
        @Element(name = "BeheerderContact", required = false) set
        @Element(name = "BeheerderContact", required = false) get
}
