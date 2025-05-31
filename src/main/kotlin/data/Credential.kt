package data

import java.util.UUID

data class Credential(
    val id: UUID = UUID.randomUUID(),
    val username: HistoryTrackedField<String>,
    val password: HistoryTrackedField<String>,
    val url: HistoryTrackedField<String>,
    val notes: HistoryTrackedField<String>
) {
    companion object {
        fun create(username: String, password: String, url: String, notes: String): Credential {
            return Credential(
                username = HistoryTrackedField(username),
                password = HistoryTrackedField(password),
                url = HistoryTrackedField(url),
                notes = HistoryTrackedField(notes)
            )
        }
    }
}
