package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.hidden_balance
import fintrack.composeapp.generated.resources.password_visible
import id.my.sendiko.fintrack.dashboard.data.wallets
import id.my.sendiko.fintrack.dashboard.presentation.toRupiah
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.wallet.domain.Wallet
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: Wallet,
    isVisible: Boolean = false,
    onVisibilityToggle: (Boolean) -> Unit,
) {
    Card(
        modifier = modifier.width(256.dp)
            .height(128.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = secondaryBlue,
            contentColor = utilityWhite
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = wallet.name
                )
                Text(
                    text = wallet.number.takeLast(4),
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isVisible)
                        wallet.amount.toRupiah()
                    else stringResource(Res.string.hidden_balance),
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(
                    onClick = { onVisibilityToggle(!isVisible) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = stringResource(Res.string.password_visible)
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
private fun WalletCardPreview() {
    FinTrackTheme {
        WalletCard(
            wallet = wallets.first(),
            modifier = Modifier,
            isVisible = false,
            onVisibilityToggle = {  }
        )
    }
}