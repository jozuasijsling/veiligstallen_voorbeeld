package nl.jozuasijsling.veiligstallen.test

import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import org.simpleframework.xml.core.Persister


fun deserializeFromResource(filename: String, deserializer: Persister = Persister()) =
        ClassLoader.getSystemResourceAsStream(filename).use {
            deserializer.read(SafeStorageDto::class.java, it)
        }!!
