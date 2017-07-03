package nl.jozuasijsling.veiligstallen.service.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.math.BigDecimal

@Root(name = "Abonnement")
class SubscriptionDto(

        @param:Element(name = "Naam", required = false)
        @get:Element(name = "Naam", required = false)
        val name: String,

        @param:Element(name = "Prijs")
        @get:Element(name = "Prijs")
        val price: BigDecimal
)
