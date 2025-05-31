package ui.components.folder_tree_view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Directory

@Composable
fun FolderTreeView(directory: Directory, onDirectorySelected: (Directory) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item {
            FolderItem(directory, 0, onDirectorySelected)
        }
    }
}
