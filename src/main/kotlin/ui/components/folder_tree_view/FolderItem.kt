package ui.components.folder_tree_view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Directory
import ui.components.RotatingIconToggle
import javax.swing.text.StyledEditorKit

@Composable
fun FolderStateIcon(
    isOpen: Boolean,
    onClick: (Boolean) -> Unit
) {
    Icon(
        painter = painterResource(if(isOpen) "folder_open.svg" else "folder.svg"),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(!isOpen) }
            .padding(8.dp)
    )
}

@Composable
fun FolderItem(directory: Directory, level: Int = 0, onDirectorySelected: (Directory) -> Unit) {
    Column {
        var isExpanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                // .border(1.dp, color = Color.Red)
                .fillMaxWidth()
                .padding(start = (level * 16).dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            FolderStateIcon(isExpanded) { isExpanded = it }

            Text(
                directory.name.value,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onDirectorySelected(directory)
                    }
                    .padding(12.dp)
            )
        }

        if (isExpanded) {
            directory.getSubDirectories().forEach { subDirectory ->
                FolderItem(subDirectory, level + 1, onDirectorySelected)
            }
        }
    }
}

