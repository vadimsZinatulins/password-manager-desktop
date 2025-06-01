package data

import java.util.UUID

class Directory(name: String) {
    val id: UUID = UUID.randomUUID()
    val name = HistoryTrackedField(name)
    private val subDirectories = mutableListOf<Directory>()
    private val credentials = mutableListOf<Credential>()

    fun addDirectory(directory: Directory) {
        subDirectories.add(directory)

        DirectoryTracker.markAsDirty()
    }

    fun addCredential(credential: Credential) {
        credentials.add(credential)

        DirectoryTracker.markAsDirty()
    }

    fun getSubDirectories(): List<Directory> = subDirectories.toList()

    fun getCredentials(): List<Credential> = credentials.toList()
}
