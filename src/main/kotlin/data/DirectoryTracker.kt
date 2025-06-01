package data

import androidx.compose.runtime.mutableStateOf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

object DirectoryTracker : Serializable {
    var rootDirectory = mutableStateOf<Directory?>(null)
        private set

    var currentDirectory = mutableStateOf<Directory?>(null)
        private set

    var isDirty = mutableStateOf(false)
        private set

    fun setRootDirectory(directory: Directory) {
        rootDirectory.value = directory
        currentDirectory.value = directory
        isDirty.value = false
    }

    fun setCurrentDirectory(directory: Directory) {
        currentDirectory.value = directory
        isDirty.value = false
    }

    fun markAsDirty() {
        isDirty.value = true
    }

    fun serialize(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        ObjectOutputStream(byteStream).use {
            it.writeObject(this)
        }
        return byteStream.toByteArray()
    }

    fun deserialize(data: ByteArray): DirectoryTracker {
        val byteStream = ByteArrayInputStream(data)
        val obj = ObjectInputStream(byteStream).use {
            it.readObject()
        }
        return obj as DirectoryTracker
    }

    private fun readResolve(): Any = DirectoryTracker
}