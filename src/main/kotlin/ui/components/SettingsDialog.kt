package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState

@Composable
fun SettingsDialog(
    isOpen: Boolean,
    onClose: () -> Unit,
) {
    if(isOpen) {
        DialogWindow(
            onCloseRequest = { onClose() },
            state = rememberDialogState(position = WindowPosition(Alignment.Center))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                Row {
                    var isAutosaveEnabled by remember { mutableStateOf(false) }
                    Text(
                        text = "Autosave",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Switch(
                        checked = isAutosaveEnabled,
                        onCheckedChange = { isAutosaveEnabled = it },
                    )
                }
                /*
                var location by remember { mutableStateOf("Europe/Paris") }

                TextField(
                    value = location,
                    onValueChange = { location = it }
                )
                */

            }
        }
    }
}
