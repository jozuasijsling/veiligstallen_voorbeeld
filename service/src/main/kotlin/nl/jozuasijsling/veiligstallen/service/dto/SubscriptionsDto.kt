package nl.jozuasijsling.veiligstallen.service.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Abonnementen")
class SubscriptionsDto(

        @param:ElementList(name = "Abonnement", inline = true)
        @get:ElementList(name = "Abonnement", inline = true)
        val list: List<SubscriptionDto>,

        @param:Element(name = "AbonnementURL", required = false)
        @get:Element(name = "AbonnementURL", required = false)
        val url: String
)
