package nl.jozuasijsling.veiligstallen.content

import nl.jozuasijsling.veiligstallen.content.shed.Address
import nl.jozuasijsling.veiligstallen.content.shed.GeoLocation
import nl.jozuasijsling.veiligstallen.content.shed.InnerSection
import nl.jozuasijsling.veiligstallen.content.shed.OpeningHours
import nl.jozuasijsling.veiligstallen.service.dto.BikeShedDto
import nl.jozuasijsling.veiligstallen.service.dto.OpeningHoursDto
import nl.jozuasijsling.veiligstallen.service.dto.SectionDto
import okhttp3.HttpUrl


fun BikeShedDto.toDomainObject(): BikeShed {
    return BikeShed(name!!, description!!, id!!,
            extractAddress(), extractCoordinates(), HttpUrl.parse(url!!)!!,
            bikeCapacity!!, capacityTotal!!,
            referral!!.first().let { Integer.parseInt(it) },
            when {
                referral!!.size == 1 -> null
                else -> HttpUrl.parse(referral!![1])
            }, sections!!.map { it.toDomainObject() },
            openingHours!!.toDomainObject(),
            isStationShed == "Ja",
            facilities?.split(",") ?: emptyList(),
            type,
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
    when {
        remark != null -> return remark!!
        else -> return "${open!!.toString()} - ${closed!!.toString()}"
    }
}

fun SectionDto.toDomainObject(): InnerSection {
    return InnerSection(id!!, name!!, capacity!!, unoccupied!!, occupied!!)
}

fun BikeShedDto.extractCoordinates(): GeoLocation? {
    val coordinatesVal = coordinates
    when (coordinatesVal) {
        null -> return null
        else -> return GeoLocation(coordinatesVal[0], coordinatesVal[1])
    }
}

fun BikeShedDto.extractAddress(): Address? {
    if (municipality == null
            && street == null
            && postcode == null
            && city == null) {
        return null
    } else {
        return Address(municipality ?: "", street ?: "", postcode ?: "", city ?: "")
    }
}
