package id.my.sendiko.fintrack.dashboard.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.contactless_icon_desc
import fintrack.composeapp.generated.resources.ic_contactless
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.redError
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import id.my.sendiko.fintrack.wallet.core.domain.WalletType
import id.my.sendiko.fintrack.wallet.core.domain.WalletType.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    walletType: WalletType,
    name: String,
    number: String,
    balanceAmount: Double,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .widthIn(min = 280.dp)
            .width(IntrinsicSize.Max) // Forces Card + inner Box/Row to resolve to the exact same width (>= 280.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (walletType) {
                CARD -> secondaryBlue
                E_MONEY -> primaryOrange
                CASH -> greenApproved
            },
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Canvas(
                modifier = Modifier.matchParentSize(),
            ) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.08f),
                    radius = size.width * 0.45f,
                    center = Offset(x = size.width * 0.88f, y = size.height * 0.85f),
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(
                        modifier = Modifier.weight(1f, fill = false),
                    ) {
                        Text(
                            text = walletType.name,
                            color = utilityWhite.copy(alpha = 0.7f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = name,
                            color = utilityWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        painter = painterResource(Res.drawable.ic_contactless),
                        contentDescription = stringResource(Res.string.contactless_icon_desc),
                        tint = utilityWhite.copy(alpha = 0.9f),
                        modifier = Modifier.size(28.dp),
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "XXXX${number.takeLast(4)}",
                    color = utilityWhite.copy(alpha = 0.75f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = balanceAmount.toRupiah(),
                    color = utilityWhite,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    softWrap = false,
                )
            }
        }
    }
}

@Preview
@Composable
private fun WalletCardPreview() {
    FinTrackTheme {
        WalletCard(
            walletType = CASH,
            name = "Main Jago",
            number = "707022680004",
            balanceAmount = 12000000.0
        )
    }
}
