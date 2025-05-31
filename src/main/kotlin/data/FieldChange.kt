package data

import java.time.LocalDateTime

data class FieldChange<T>(
    val timestamp: LocalDateTime,
    val from: T?,
    val to: T?
)
