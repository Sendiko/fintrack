package com.github.sendiko.fintrack.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.sendiko.fintrack.core.presentation.BaseTextField
import com.github.sendiko.fintrack.core.presentation.SecureTextField
import com.github.sendiko.fintrack.theme.FinTrackTheme
import com.github.sendiko.fintrack.theme.primaryOrange
import com.github.sendiko.fintrack.theme.secondaryBlue
import com.github.sendiko.fintrack.theme.utilityWhite
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.app_name
import fintrack.composeapp.generated.resources.counts
import fintrack.composeapp.generated.resources.create_password_hint
import fintrack.composeapp.generated.resources.create_username_hint
import fintrack.composeapp.generated.resources.email_hint
import fintrack.composeapp.generated.resources.email_label
import fintrack.composeapp.generated.resources.every_transaction
import fintrack.composeapp.generated.resources.fintrack_white
import fintrack.composeapp.generated.resources.login_hint
import fintrack.composeapp.generated.resources.password_label
import fintrack.composeapp.generated.resources.register_hint
import fintrack.composeapp.generated.resources.register_title
import fintrack.composeapp.generated.resources.terms_and_condition_agreement
import fintrack.composeapp.generated.resources.username_label
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigate: (Any) -> Unit,
) {

    val uriHandler = LocalUriHandler.current

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
                    text = stringResource(Res.string.every_transaction),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(Res.string.counts),
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
                            text = stringResource(Res.string.register_title),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(Res.string.register_hint),
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
                            onValueChange = { onEvent(RegisterEvent.OnUsernameChanged(it)) },
                            hint = stringResource(Res.string.create_username_hint),
                        )
                    }
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
                            onValueChange = { onEvent(RegisterEvent.OnEmailChanged(it)) },
                            hint = stringResource(Res.string.email_hint),
                            keyboardType = KeyboardType.Email
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
                            onValueChange = { onEvent(RegisterEvent.OnPasswordChanged(it)) },
                            hint = stringResource(Res.string.create_password_hint),
                            onVisibilityChanged = { onEvent(RegisterEvent.OnPasswordVisibilityChanged(it)) },
                            isVisible = state.passwordVisible
                        )
                    }
                    Surface(
                        onClick = { onEvent(RegisterEvent.OnTermsCheckChanged(!state.termsChecked)) }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = state.termsChecked,
                                onCheckedChange = { onEvent(RegisterEvent.OnTermsCheckChanged(!state.termsChecked)) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = secondaryBlue,
                                    checkmarkColor = utilityWhite,
                                )
                            )
                            Surface(
                                onClick = { uriHandler.openUri("https://fintrack.sendiko.my.id/") }
                            ) {
                                Text(
                                    text = stringResource(Res.string.terms_and_condition_agreement),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
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
                            text = stringResource(Res.string.register_title),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = { },
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

@Preview
@Composable
fun RegisterScreenPrev() {
    FinTrackTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {  },
            onNavigate = {  }
        )
    }
}