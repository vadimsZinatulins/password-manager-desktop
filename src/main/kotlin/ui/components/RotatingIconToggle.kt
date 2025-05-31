package ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun RotatingIconToggle(isExpanded: Boolean, onToggle: () -> Unit) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        label = "RotationAnimation"
    )

    Icon(
        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onToggle() }
            .rotate(rotationAngle)
            .padding(8.dp)
    )
}
