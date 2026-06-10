package id.my.sendiko.fintrack.wallet.form.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.confirm
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.create_wallet_title
import fintrack.composeapp.generated.resources.delete_wallet_body
import fintrack.composeapp.generated.resources.delete_wallet_headline
import fintrack.composeapp.generated.resources.initial_balance
import fintrack.composeapp.generated.resources.next
import fintrack.composeapp.generated.resources.update_wallet_title
import fintrack.composeapp.generated.resources.wallet_name_hint
import fintrack.composeapp.generated.resources.wallet_name_label
import fintrack.composeapp.generated.resources.wallet_number_hint
import fintrack.composeapp.generated.resources.wallet_number_label
import fintrack.composeapp.generated.resources.wallet_number_note
import fintrack.composeapp.generated.resources.wallet_purpose_hint
import fintrack.composeapp.generated.resources.wallet_purpose_label
import fintrack.composeapp.generated.resources.wallet_type_hint
import fintrack.composeapp.generated.resources.wallet_type_label
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.StageBar
import id.my.sendiko.fintrack.core.presentation.numerickeyboard.NumericKeyboard
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.core.presentation.textfields.DropdownMenu
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.redError
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.wallet.form.presentation.components.FormWalletTopBar
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormWalletScreen(
    state: FormWalletState,
    onEvent: (FormWalletEvent) -> Unit,
    onNavigate: (Any?) -> Unit,
) {

    LaunchedEffect(state.walletId) {
        if (state.walletId.isNotBlank())
            onEvent(FormWalletEvent.OnLoadWallet)
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            delay(2.seconds)
            onNavigate(null)
        }
    }

    if (state.showDeleteDialog) {
        BasicAlertDialog(
            onDismissRequest = { onEvent(FormWalletEvent.OnDismissDeleteDialog) },
            content = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.delete_wallet_headline),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = stringResource(Res.string.delete_wallet_body),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                shape = RoundedCornerShape(
                                    topEnd = 4.dp,
                                    topStart = 8.dp,
                                    bottomEnd = 4.dp,
                                    bottomStart = 8.dp
                                ),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = secondaryBlue,
                                ),
                                border = BorderStroke(1.dp, secondaryBlue),
                                onClick = { onEvent(FormWalletEvent.OnDismissDeleteDialog) },
                                modifier = Modifier.weight(2f),
                            ) {
                                Text(stringResource(Res.string.cancel))
                            }
                            Spacer(Modifier.width(2.dp))
                            Button(
                                modifier = Modifier.weight(3f),
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 8.dp,
                                    bottomStart = 4.dp,
                                    bottomEnd = 8.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = redError,
                                    contentColor = utilityWhite
                                ),
                                onClick = { onEvent(FormWalletEvent.OnDelete) }
                            ) {
                                Text(stringResource(Res.string.confirm))
                            }
                        }
                    }
                }
            }
        )
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
        content = {
            Scaffold(
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                bottomBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            shape = if (state.walletId.isBlank())
                                RoundedCornerShape(16.dp)
                            else RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 4.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 4.dp
                            ),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            onClick = {
                                if (state.stage == 1) {
                                    onEvent(FormWalletEvent.OnNext)
                                } else {
                                    onEvent(FormWalletEvent.OnSave)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryOrange,
                            )
                        ) {
                            Text(
                                text = when (state.stage) {
                                    1 -> stringResource(Res.string.next)
                                    2 -> stringResource(Res.string.create)
                                    else -> ""
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (state.walletId.isNotBlank()) {
                            Spacer(Modifier.width(4.dp))
                            IconButton(
                                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = redError,
                                    contentColor = utilityWhite
                                ),
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 16.dp,
                                    bottomStart = 4.dp,
                                    bottomEnd = 16.dp
                                ),
                                onClick = { onEvent(FormWalletEvent.OnShowDeleteDialog) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    FormWalletTopBar(
                        title = if (state.walletId.isBlank())
                            stringResource(Res.string.create_wallet_title)
                        else stringResource(Res.string.update_wallet_title),
                        onNavigate = { onNavigate(null) }
                    )
                    AnimatedContent(
                        targetState = state.stage,
                        transitionSpec = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                                .togetherWith(slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left))
                        }
                    ) { pageStage ->
                        StageBar(stage = state.stage)
                        when (pageStage) {
                            1 -> {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_name_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.name,
                                            onValueChange = {
                                                onEvent(
                                                    FormWalletEvent.OnNameChanged(
                                                        it
                                                    )
                                                )
                                            },
                                            hint = stringResource(Res.string.wallet_name_hint),
                                            outlineColor = secondaryBlue
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_type_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        DropdownMenu(
                                            modifier = Modifier.fillMaxWidth(),
                                            items = state.walletTypeList,
                                            onChosen = { onEvent(FormWalletEvent.OnTypeChanged(it.name)) },
                                            hint = stringResource(Res.string.wallet_type_hint)
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_purpose_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.purpose,
                                            onValueChange = {
                                                onEvent(
                                                    FormWalletEvent.OnPurposeChanged(
                                                        it
                                                    )
                                                )
                                            },
                                            hint = stringResource(Res.string.wallet_purpose_hint),
                                            outlineColor = secondaryBlue
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_number_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.number,
                                            onValueChange = {
                                                onEvent(
                                                    FormWalletEvent.OnWalletNumberChanged(
                                                        it
                                                    )
                                                )
                                            },
                                            hint = stringResource(Res.string.wallet_number_hint),
                                            outlineColor = secondaryBlue,
                                            supportingText = stringResource(Res.string.wallet_number_note)
                                        )
                                    }
                                }
                            }

                            2 -> {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .padding(top = 16.dp)
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = stringResource(Res.string.initial_balance),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = (state.amount.toDoubleOrNull() ?: 0.0).toRupiah(),
                                        style = MaterialTheme.typography.headlineLarge,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        color = secondaryBlue
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    NumericKeyboard(
                                        onClick = {
                                            onEvent(FormWalletEvent.OnNumberPressed(it))
                                        },
                                        onBackspace = {
                                            onEvent(FormWalletEvent.OnBackspace)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun FormWalletScreenPreview() {
    FinTrackTheme {
        FormWalletScreen(
            state = FormWalletState(
                stage = 2
            ),
            onEvent = { },
            onNavigate = { }
        )
    }
}