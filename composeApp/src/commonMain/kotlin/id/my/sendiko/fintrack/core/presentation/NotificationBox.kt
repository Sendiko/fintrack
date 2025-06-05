package id.my.sendiko.fintrack.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationBox(
    message: String,
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = isLoading,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        AnimatedVisibility(
            visible = message.isNotBlank(),
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        content()
    }
}