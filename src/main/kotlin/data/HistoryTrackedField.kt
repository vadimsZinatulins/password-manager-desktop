package data

import java.time.LocalDateTime

class HistoryTrackedField<T>(initialValue: T) {
    private val history = mutableListOf<FieldChange<T>>()
    var value: T = initialValue
        set(newValue) {
            val change = FieldChange(LocalDateTime.now(), field, newValue)
            history.add(change)
            field = newValue
        }

    fun getHistory(): List<FieldChange<T>> = history.toList()
}
