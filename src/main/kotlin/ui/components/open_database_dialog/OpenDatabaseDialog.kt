package ui.components.open_database_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
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
fun OpenDatabaseDialog(
    onCloseRequest: () -> Unit,
    onSignIn: () -> Unit,
) {
    var epmFile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    DialogWindow(
        onCloseRequest = onCloseRequest,
        state = rememberDialogState(position = WindowPosition(Alignment.Center))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            FilePathSelector(epmFile) { newFilePath ->
                epmFile = newFilePath
            }

            Button(onClick = { onSignIn() }) {
                Text("Sign In")
            }
        }
    }
}
