package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Directory

@Composable
fun FolderItem(directory: Directory, level: Int = 0, onDirectorySelected: (Directory) -> Unit) {
    Column {
        var isExpanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = (level * 16).dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RotatingIconToggle(isExpanded) { isExpanded = !isExpanded }

            Text(
                directory.name.value,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                    onDirectorySelected(directory)
                })
        }

        if (isExpanded) {
            directory.getSubDirectories().forEach { subDirectory ->
                FolderItem(subDirectory, level + 1, onDirectorySelected)
            }
        }
    }
}

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
