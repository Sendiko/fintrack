package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.analytics_label
import fintrack.composeapp.generated.resources.expense_label
import fintrack.composeapp.generated.resources.ic_analytics
import fintrack.composeapp.generated.resources.ic_minus
import fintrack.composeapp.generated.resources.ic_plus
import fintrack.composeapp.generated.resources.ic_transfer
import fintrack.composeapp.generated.resources.income_label
import fintrack.composeapp.generated.resources.total_net_worth
import fintrack.composeapp.generated.resources.transfer_label
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TotalNetWorthCard(
    modifier: Modifier = Modifier,
    totalNetWorth: Double,
    onIncomeClick: () -> Unit,
    onExpenseClick: () -> Unit,
    onTransferClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF425E6E),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                text = stringResource(Res.string.total_net_worth),
                color = utilityWhite.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = totalNetWorth.toRupiah(),
                color = utilityWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    label = stringResource(Res.string.income_label),
                    iconRes = Res.drawable.ic_plus,
                    circleColor = greenApproved,
                    iconTint = utilityWhite,
                    onClick = onIncomeClick,
                )

                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    label = stringResource(Res.string.expense_label),
                    iconRes = Res.drawable.ic_minus,
                    circleColor = primaryOrange,
                    iconTint = utilityWhite,
                    onClick = onExpenseClick,
                )

                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    label = stringResource(Res.string.transfer_label),
                    iconRes = Res.drawable.ic_transfer,
                    circleColor = Color(0xFFD0E6FA),
                    iconTint = secondaryBlue,
                    onClick = onTransferClick,
                )

                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    label = stringResource(Res.string.analytics_label),
                    iconRes = Res.drawable.ic_analytics,
                    circleColor = utilityWhite,
                    iconTint = secondaryBlue,
                    onClick = onAnalyticsClick,
                )
            }
        }
    }
}

@Composable
private fun QuickActionButton(
    modifier: Modifier = Modifier,
    label: String,
    iconRes: DrawableResource,
    circleColor: Color,
    iconTint: Color = utilityWhite,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF4D6979))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(color = circleColor, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = label,
                color = utilityWhite,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun TotalNetWorthCardPreview() {
    FinTrackTheme {
        TotalNetWorthCard(
            totalNetWorth = 1500000.0,
            onIncomeClick = {  },
            onExpenseClick = {  },
            onTransferClick = {  },
            onAnalyticsClick = {  }
        )
    }
}
