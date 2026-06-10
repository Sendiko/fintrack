package id.my.sendiko.fintrack.wallet.list.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.confirm
import fintrack.composeapp.generated.resources.delete_wallet_body
import fintrack.composeapp.generated.resources.delete_wallet_headline
import fintrack.composeapp.generated.resources.empty_wallet
import fintrack.composeapp.generated.resources.wallet_list_title
import id.my.sendiko.fintrack.core.navigation.FormWalletDestination
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.redError
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.wallet.list.presentation.components.EditableWalletCard
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletListScreen(
    state: WalletListState,
    onEvent: (WalletListEvent) -> Unit,
    onNavigate: (Any?) -> Unit,
) {

    LaunchedEffect(true) {
        onEvent(WalletListEvent.OnLoadData)
    }

    if (state.showDeleteDialog) {
        BasicAlertDialog(
            onDismissRequest = { onEvent(WalletListEvent.OnDismissDeleteDialog) },
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
                                onClick = { onEvent(WalletListEvent.OnDismissDeleteDialog) },
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
                                onClick = { onEvent(WalletListEvent.OnDelete) }
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
        isLoading = state.isLoading
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.wallet_list_title),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onNavigate(null) },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                )
            },
        ) { paddingValues ->
            if (state.wallets.isNotEmpty()) {
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.wallets) {
                        EditableWalletCard(
                            modifier = Modifier.fillMaxWidth(),
                            wallet = it,
                            isVisible = state.balanceVisible,
                            onVisibilityToggle = { onEvent(WalletListEvent.OnBalanceViewChanged(it)) },
                            onEdit = {
                                onNavigate(FormWalletDestination(it.id))
                            },
                            onDelete = { onEvent(WalletListEvent.OnShowDeleteDialog(it.id)) },
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.empty_wallet),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun WalletListScreenPreview() {
    FinTrackTheme {
        WalletListScreen(
            state = WalletListState(),
            onEvent = { },
            onNavigate = { }
        )
    }
}