package com.github.sendiko.fintrack.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.sendiko.fintrack.core.navigation.LoginDestination
import com.github.sendiko.fintrack.theme.FinTrackTheme
import com.github.sendiko.fintrack.theme.primaryOrange
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.app_name
import fintrack.composeapp.generated.resources.fintrack_blue
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    state: SplashState,
    onNavigate: (Any) -> Unit
) {

    LaunchedEffect(state.token) {
        if (state.token.isBlank()) {
            delay(1000)
            onNavigate(LoginDestination)
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = primaryOrange
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.padding(64.dp),
                painter = painterResource(Res.drawable.fintrack_blue),
                contentDescription = stringResource(Res.string.app_name)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPrev() {
    FinTrackTheme {
        SplashScreen(
            state = SplashState(),
            modifier = Modifier,
            onNavigate = {}
        )
    }
}