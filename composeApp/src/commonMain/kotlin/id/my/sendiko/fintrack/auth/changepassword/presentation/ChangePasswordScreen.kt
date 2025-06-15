package id.my.sendiko.fintrack.auth.changepassword.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.account_is_safe
import fintrack.composeapp.generated.resources.app_name
import fintrack.composeapp.generated.resources.change_password_hint
import fintrack.composeapp.generated.resources.change_password_label
import fintrack.composeapp.generated.resources.verify_hint
import fintrack.composeapp.generated.resources.change_password_title
import fintrack.composeapp.generated.resources.dont_worry
import fintrack.composeapp.generated.resources.email_hint
import fintrack.composeapp.generated.resources.email_label
import fintrack.composeapp.generated.resources.fintrack_white
import fintrack.composeapp.generated.resources.login_hint
import fintrack.composeapp.generated.resources.password_label
import fintrack.composeapp.generated.resources.verify_label
import id.my.sendiko.fintrack.core.navigation.LoginDestination
import id.my.sendiko.fintrack.core.presentation.BaseTextField
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.SecureTextField
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    state: ChangePasswordState,
    onEvent: (ChangePasswordEvent) -> Unit,
    onNavigate: (Any) -> Unit,
) {

    LaunchedEffect(state.message) {
        if (state.message.isNotBlank()) {
            delay(1000)
            onEvent(ChangePasswordEvent.ClearState)
        }
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading
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
                        text = stringResource(Res.string.dont_worry),
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Bold,
                        color = utilityWhite
                    )
                    Text(
                        text = stringResource(Res.string.account_is_safe),
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
                                text = stringResource(Res.string.change_password_title),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(
                                    resource = if (state.userId.isBlank())
                                        Res.string.verify_hint
                                    else Res.string.change_password_hint
                                ),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        AnimatedVisibility(
                            visible = state.userId.isBlank(),
                            exit = fadeOut(),
                            enter = fadeIn()
                        ) {
                            Column {
                                Text(
                                    text = stringResource(Res.string.email_label),
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                BaseTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    value = state.email,
                                    onValueChange = { onEvent(ChangePasswordEvent.OnEmailChanged(it)) },
                                    hint = stringResource(Res.string.email_hint),
                                    keyboardType = KeyboardType.Email
                                )
                            }
                        }
                        AnimatedVisibility(
                            visible = state.userId.isNotBlank(),
                            exit = fadeOut(),
                            enter = fadeIn()
                        ) {
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
                                    onValueChange = {
                                        onEvent(
                                            ChangePasswordEvent.OnPasswordChanged(
                                                it
                                            )
                                        )
                                    },
                                    hint = stringResource(Res.string.change_password_hint),
                                    onVisibilityChanged = {
                                        onEvent(ChangePasswordEvent.OnPasswordVisibilityChanged(it))
                                    },
                                    isVisible = state.isPasswordVisible
                                )
                            }
                        }
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            onClick = {
                                if (state.userId.isBlank())
                                    onEvent(ChangePasswordEvent.OnVerifyEmail)
                                else onEvent(ChangePasswordEvent.OnChangePassword)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = secondaryBlue,
                            )
                        ) {
                            Text(
                                text = stringResource(
                                    resource = if (state.userId.isBlank())
                                        Res.string.verify_label
                                    else Res.string.change_password_label
                                ),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            onClick = { onNavigate(LoginDestination) },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = secondaryBlue
                            )
                        ) {
                            Text(
                                text = stringResource(Res.string.login_hint),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPrev() {
    ChangePasswordScreen(
        state = ChangePasswordState(),
        onEvent = { },
        onNavigate = { }
    )
}