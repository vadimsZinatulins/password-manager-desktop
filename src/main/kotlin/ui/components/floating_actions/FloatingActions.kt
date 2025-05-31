package ui.components.floating_actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActions(
    onSettingsOpen: () -> Unit,
    onSync: () -> Unit,
    onShowDatabase: () -> Unit,
    onAddCredential: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
        verticalAlignment = Alignment.Bottom
    ) {
        FloatingActionButton(
            modifier = Modifier.size(38.dp),
            onClick = onSettingsOpen
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Open Settings",
                modifier = Modifier.size(14.dp)
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(38.dp),
            onClick = onSync
        ) {
            Icon(
                painter = painterResource("sync.svg"),
                contentDescription = "Synchronize",
                modifier = Modifier.size(24.dp)
            )
        }
        FloatingActionButton(onClick = onShowDatabase) {
            Icon(
                painter = painterResource("database.svg"),
                contentDescription = "Show Database",
                modifier = Modifier.size(48.dp)
            )
        }
        FloatingActionButton(onClick = onAddCredential) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add Credential",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}