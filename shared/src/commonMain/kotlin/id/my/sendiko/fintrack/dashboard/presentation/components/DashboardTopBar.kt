package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.greeting
import fintrack.composeapp.generated.resources.notifications
import id.my.sendiko.fintrack.core.presentation.buttons.IconButton
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.bodyLarge
import id.my.sendiko.fintrack.core.presentation.theme.bodyMedium
import id.my.sendiko.fintrack.core.presentation.theme.labelLarge
import id.my.sendiko.fintrack.core.presentation.theme.labelMedium
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun DashboardTopBar(
    modifier: Modifier = Modifier,
    username: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.greeting),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = username,
                style = MaterialTheme.typography.titleLarge
            )
        }
        IconButton(
            icon = Icons.Default.Notifications,
            onClick = {  },
            contentDescription = stringResource(Res.string.notifications)
        )
    }
}

@Preview
@Composable
fun DashboardTopBarPrev() {
    FinTrackTheme {
        Surface {
            DashboardTopBar(
                modifier = Modifier.fillMaxWidth(),
                username = "Sendiko"
            )
        }
    }
}