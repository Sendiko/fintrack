package id.my.sendiko.fintrack.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.secondaryBlue

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
        content()
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = secondaryBlue
            )
        }
        AnimatedVisibility(
            visible = message.isNotBlank(),
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Surface(
                modifier = Modifier.statusBarsPadding().padding(top = 16.dp),
                color = secondaryBlue,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}