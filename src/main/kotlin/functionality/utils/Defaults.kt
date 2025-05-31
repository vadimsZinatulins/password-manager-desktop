package functionality.utils

import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.ConcurrentHashMap

object Defaults {
    private val applicationName = "passwordmanagerdesktop"
    private val cacheFileName = ".defaults"
    private val props = Properties()
    private val data = ConcurrentHashMap<String, String>()

    private val file: File by lazy {
        val dir = getCacheDir().apply { mkdirs() }
        File(dir, cacheFileName).also {
            if(it.exists()) {
                FileInputStream(it).use { input ->
                    props.load(input)
                    props.forEach { (key, value) ->
                        data[key.toString()] = value.toString()
                    }
                }
            }
        }
    }

    fun get(key: String) = data[key]

    fun set(key: String, value: String) {
        data[key] = value
        props[key] = value

        save()
    }

    private fun getCacheDir(): File {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        return when {
            os.contains("mac") -> File("$userHome/Library/Caches/$applicationName")
            os.contains("win") -> File(System.getenv("LOCALAPPDATA") + "\\$applicationName\\cache")
            else -> File("$userHome/.cache/$applicationName")
        }
    }

    fun save() {
        file.outputStream().use { output ->
            props.store(output, "Defaults for $applicationName")
        }
    }

    val DEFAULT_EPM_FILE_PATH: String = "default_epm_file_path"
}