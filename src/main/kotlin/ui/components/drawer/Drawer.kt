package ui.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.Directory
import kotlinx.coroutines.launch
import ui.components.FolderTreeView

@Composable
fun Drawer(
    scaffoldState: ScaffoldState,
    rootDirectory: Directory,
    onDirectorySelected: (Directory) -> Unit,
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {}) {
                Text("Load database")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource("create_new_folder.svg"),
                    contentDescription = "Create New Folder",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Green)
                        .clickable {

                        }
                        .padding(8.dp)
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Red)
                        .clickable {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                        .padding(8.dp)
                )
            }
        }
        FolderTreeView(rootDirectory) {
            onDirectorySelected(it)
        }
    }
}