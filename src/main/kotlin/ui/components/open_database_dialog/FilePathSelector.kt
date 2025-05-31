package ui.components.open_database_dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilePathSelector(
    filePath: String,
    onFilePathChange: (String) -> Unit
) {
    var isFilePickerOpen by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = filePath,
            onValueChange = { onFilePathChange(it) },
            modifier = Modifier.weight(1f).height(56.dp),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )

        Button(
            onClick = { isFilePickerOpen = true }, modifier = Modifier.height(56.dp),
            shape = RoundedCornerShape(8.dp)
        ) { Text("Change") }
    }

    if (isFilePickerOpen) {
        FileDialog { result ->
            result?.let { onFilePathChange(it) }
            isFilePickerOpen = false
        }
    }
}
