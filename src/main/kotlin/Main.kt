import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.Credential
import data.Directory
import functionality.utils.Defaults
import kotlinx.coroutines.launch
import ui.components.FolderTreeView
import ui.components.SettingsDialog
import ui.components.open_database_dialog.OpenDatabaseDialog
import ui.components.Table
import ui.components.drawer.Drawer
import ui.components.floating_actions.FloatingActions
import kotlin.random.Random

fun randomString(length: Int = 12): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length).map { chars.random() }.joinToString("")
}

fun generateCredential() = Credential.create(
    username = randomString(Random.nextInt(4, 16)),
    password = randomString(Random.nextInt(8, 20)),
    url = "https://${randomString(Random.nextInt(4, 12))}.com",
    notes = randomString(Random.nextInt(10, 50))
)

fun generateDirectory(genSubDirectories: Boolean = false): Directory =
    Directory(randomString(Random.nextInt(4, 12))).apply {
        repeat(Random.nextInt(5, 50)) {
            addCredential(generateCredential())
        }
        if (genSubDirectories) {
            repeat(Random.nextInt(1, 4)) {
                val genMore = Random.nextBoolean()

                addDirectory(generateDirectory(genMore))
            }
        }
    }

@Composable
@Preview
fun App() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val rootDirectory = Directory("Unsaved") // generateDirectory(true)

    var currentViewingDirectory by remember { mutableStateOf(rootDirectory) }

    var isDialogOpen by remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                Drawer(scaffoldState, rootDirectory) { selectedDirectory ->
                    currentViewingDirectory = selectedDirectory
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            },
            floatingActionButton = {
                FloatingActions(
                    {},
                    {},
                    {},
                    {}
                )
            }
        ) {
            SettingsDialog(isDialogOpen) { isDialogOpen = false }

            Table(currentViewingDirectory)
        }
    }
}

fun main() = application {
    var isSignedIn by remember { mutableStateOf(true) }
    var epmFilePath by remember { mutableStateOf(Defaults.get(Defaults.DEFAULT_EPM_FILE_PATH)) }

    epmFilePath?.let {
        if(isSignedIn) {
            Window(onCloseRequest = ::exitApplication, title = "Password Manager") {
                App()
            }
        } else {
            OpenDatabaseDialog(onCloseRequest = ::exitApplication) {
                isSignedIn = true
            }
        }
    } ?: run {
        Window(onCloseRequest = ::exitApplication, title = "Password Manager") {
            App()
        }
    }
}
