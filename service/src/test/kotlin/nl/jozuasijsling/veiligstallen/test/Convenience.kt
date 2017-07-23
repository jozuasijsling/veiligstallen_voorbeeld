package nl.jozuasijsling.veiligstallen.test

import nl.jozuasijsling.veiligstallen.service.dto.SafeStorageDto
import org.simpleframework.xml.core.Persister
import java.io.InputStream


fun deserializeFromResource(filename: String, deserializer: Persister = Persister()) =
        onFileResource(filename) { deserializer.read(SafeStorageDto::class.java, it)!! }

fun <T> onFileResource(filename: String, code: (InputStream) -> T) =
        ClassLoader.getSystemResourceAsStream(filename).use { code(it) }