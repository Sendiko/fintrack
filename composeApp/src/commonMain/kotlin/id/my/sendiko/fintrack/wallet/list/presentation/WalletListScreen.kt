package id.my.sendiko.fintrack.wallet.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.wallet_list_title
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.dashboard.presentation.components.WalletCard
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.aliceBlue
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletListScreen(
    state: WalletListState,
    onEvent: (WalletListEvent) -> Unit,
) {
    LaunchedEffect(state.wallets, state.token) {
        if (state.wallets.isEmpty() && state.token.isNotBlank()) {
            onEvent(WalletListEvent.OnLoadData)
        }
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
                    }
                )
            },
        ){ paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(state.wallets) {
                    WalletCard(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        wallet = it,
                        isVisible = state.balanceVisible,
                        onVisibilityToggle = { onEvent(WalletListEvent.OnBalanceViewChanged(it)) },
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
            onEvent = { }
        )
    }
}