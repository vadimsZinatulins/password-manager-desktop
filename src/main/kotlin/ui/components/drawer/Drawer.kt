package ui.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.roundToIntRect
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import data.Directory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.components.folder_tree_view.FolderTreeView

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HoverTooltipBox(
    tooltipText: String,
    delayMillis: Long = 800,
    tooltipOffset: DpOffset = DpOffset(0.dp, 48.dp),
    content: @Composable () -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }
    var showTooltip by remember { mutableStateOf(false) }

    val density = LocalDensity.current
    var parentBounds by remember { mutableStateOf(IntRect.Zero) }

    var sss by remember { mutableStateOf<Size?>(null) }

    Box(
        modifier = Modifier
            .onGloballyPositioned {
                parentBounds = it.boundsInWindow().roundToIntRect()
                sss = it.boundsInWindow().size
            }
            .pointerMoveFilter(
                onEnter = {
                    isHovered = true
                    true
                },
                onExit = {
                    isHovered = false
                    true
                }
            )
    ) {
        content()

        LaunchedEffect(isHovered) {
            if (isHovered) {
                delay(delayMillis)
                if (isHovered) {
                    showTooltip = true
                }
            } else {
                showTooltip = false
            }
        }

        if (showTooltip) {
            val popupOffset = with(density) {
                IntOffset(
                    tooltipOffset.x.roundToPx() - (sss?.width?.toInt() ?: 0),
                     tooltipOffset.y.roundToPx()
                )
            }

            Popup(
                offset = popupOffset,
                properties = PopupProperties(focusable = false),
                content = {
                    Box(
                        modifier = Modifier
                            .background(Color(60, 60, 60))
                            .padding(8.dp)
                            .shadow(4.dp)
                    ) {
                        Text(tooltipText, color = Color.White)
                    }
                })
        }
    }
}

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
                HoverTooltipBox("Testing tooltip") {
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
                }
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