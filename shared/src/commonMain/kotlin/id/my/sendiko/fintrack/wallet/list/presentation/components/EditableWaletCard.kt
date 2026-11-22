package id.my.sendiko.fintrack.wallet.list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.redError
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import id.my.sendiko.fintrack.dashboard.presentation.components.WalletCard
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import id.my.sendiko.fintrack.wallet.core.domain.WalletType

@Composable
fun EditableWalletCard(
    modifier: Modifier = Modifier,
    wallet: Wallet,
    isVisible: Boolean = false,
    onVisibilityToggle: (Boolean) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(IntrinsicSize.Min)
    ) {
        WalletCard(
            modifier = Modifier.weight(1f),
            walletType = WalletType.valueOf(wallet.type.uppercase()),
            name = wallet.name,
            number = wallet.number,
            balanceAmount = wallet.amount
        )
        Spacer(Modifier.width(2.dp))
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            IconButton(
                modifier = Modifier.weight(1f)
                    .aspectRatio(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = greenApproved,
                    contentColor = utilityWhite
                ),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 16.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                ),
                onClick = onEdit
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
            Spacer(Modifier.height(2.dp))
            IconButton(
                modifier = Modifier.weight(1f)
                    .aspectRatio(1f),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = redError,
                    contentColor = utilityWhite
                ),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 16.dp
                ),
                onClick = onDelete
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}