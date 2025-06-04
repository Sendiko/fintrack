package id.my.sendiko.fintrack.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.app_name
import fintrack.composeapp.generated.resources.create_password_hint
import fintrack.composeapp.generated.resources.create_username_hint
import fintrack.composeapp.generated.resources.fintrack_white
import fintrack.composeapp.generated.resources.forgot_password
import fintrack.composeapp.generated.resources.login_hint_2
import fintrack.composeapp.generated.resources.login_title
import fintrack.composeapp.generated.resources.or
import fintrack.composeapp.generated.resources.password_label
import fintrack.composeapp.generated.resources.register_hint_2
import fintrack.composeapp.generated.resources.track_save_grow
import fintrack.composeapp.generated.resources.username_hint
import fintrack.composeapp.generated.resources.username_label
import fintrack.composeapp.generated.resources.youre_in_charge
import id.my.sendiko.fintrack.core.navigation.ChangePasswordDestination
import id.my.sendiko.fintrack.core.navigation.RegisterDestination
import id.my.sendiko.fintrack.core.presentation.BaseTextField
import id.my.sendiko.fintrack.core.presentation.SecureTextField
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = primaryOrange,
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(Res.drawable.fintrack_white),
                        contentDescription = stringResource(Res.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryOrange
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(PaddingValues(top = paddingValues.calculateTopPadding()))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(Res.string.track_save_grow),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(Res.string.youre_in_charge),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color = secondaryBlue,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                color = utilityWhite,
                modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 16.dp,
                        alignment = Alignment.Top
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(Res.string.login_title),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(Res.string.login_hint_2),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column {
                        Text(
                            text = stringResource(Res.string.username_label),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.username,
                            onValueChange = { onEvent(LoginEvent.OnUsernameChanged(it)) },
                            hint = stringResource(Res.string.username_hint),
                        )
                    }
                    Column {
                        Text(
                            text = stringResource(Res.string.password_label),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        SecureTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.password,
                            onValueChange = { onEvent(LoginEvent.OnPasswordChanged(it)) },
                            hint = stringResource(Res.string.create_password_hint),
                            onVisibilityChanged = { onEvent(LoginEvent.OnPasswordVisibilityChanged(it)) },
                            isVisible = state.isPasswordVisible
                        )
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = secondaryBlue,
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.login_title),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = {
                            onNavigate(RegisterDestination)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = secondaryBlue
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.register_hint_2),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = stringResource(Res.string.or),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium
                    )
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = { onNavigate(ChangePasswordDestination) },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = secondaryBlue
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.forgot_password),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    FinTrackTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {  },
            onNavigate = {  }
        )
    }
}