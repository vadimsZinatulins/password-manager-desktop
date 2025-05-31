package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import data.Credential
import data.Directory

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun Table(
    directory: Directory
) {
    val usernameWeight = 0.25f
    val passwordWeight = 0.20f
    val urlWeight = 0.30f
    val lastModifiedWeight = 0.25f

    var selectedCredential by remember { mutableStateOf<Credential?>(null) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(modifier = Modifier.background(Color.Gray)) {
                TableCell("Username", usernameWeight)
                TableCell("Password", passwordWeight)
                TableCell("URL", urlWeight)
                TableCell("Last Modified At", lastModifiedWeight)
            }
        }

        items(directory.getCredentials()) {
            var modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    selectedCredential = it
                })

            if(selectedCredential == it) {
                modifier = modifier.background(Color.LightGray)
            }

            Row(modifier = modifier) {
                TableCell(it.username.value, usernameWeight)
                TableCell("********", passwordWeight)
                TableCell(it.url.value, urlWeight)
                TableCell("", urlWeight)
            }
        }
    }
}