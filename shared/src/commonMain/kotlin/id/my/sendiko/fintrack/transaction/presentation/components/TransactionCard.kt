package id.my.sendiko.fintrack.transaction.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.core.presentation.icon.CategoryIcon
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.redError
import id.my.sendiko.fintrack.core.presentation.theme.utilityBlack
import id.my.sendiko.fintrack.transaction.core.domain.model.Transaction
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.EXPENSE
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.INCOME
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionWithCategoryAndWallet
import id.my.sendiko.fintrack.wallet.core.domain.Wallet
import id.my.sendiko.fintrack.wallet.core.domain.WalletType
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

@OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)
@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    transaction: TransactionWithCategoryAndWallet
) {
    val surfaceColor = when (transaction.transaction.type) {
        INCOME -> greenApproved.copy(alpha = 0.2f)
        EXPENSE -> redError.copy(alpha = 0.2f)
    }

    val iconColor = when (transaction.transaction.type) {
        INCOME -> greenApproved
        EXPENSE -> redError
    }

    val subTextStyle = MaterialTheme.typography.bodySmall.copy(
        color = Color(0xFF496172),
        fontSize = 13.sp,
        lineHeight = 16.sp,
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(
                        color = surfaceColor,
                        shape = RoundedCornerShape(14.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                CategoryIcon(
                    iconKey = transaction.category.icon,
                    contentDescription = transaction.category.name,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp),
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = transaction.transaction.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = utilityBlack,
                        fontSize = 17.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowRow(
                    verticalArrangement = Arrangement.Center,
                    maxLines = 2,
                    overflow = FlowRowOverflow.expandIndicator {
                        Text(
                            text = "...",
                            style = subTextStyle,
                        )
                    },
                ) {
                    Text(
                        text = transaction.category.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = subTextStyle,
                    )

                    Text(
                        text = " • ",
                        maxLines = 1,
                        style = subTextStyle.copy(fontSize = 12.sp),
                    )

                    Text(
                        text = transaction.transaction.createdAt
                            .toInstant(TimeZone.currentSystemDefault())
                            .toRelativeDateTimeString(),
                        maxLines = 1,
                        softWrap = false,
                        style = subTextStyle,
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = transaction.transaction.amount.toDouble().toRupiah(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = iconColor,
                        fontSize = 19.sp,
                    ),
                    maxLines = 1,
                    softWrap = false,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .background(
                            color = surfaceColor,
                            shape = RoundedCornerShape(6.dp),
                        )
                        .padding(horizontal = 8.dp, vertical = 3.dp),
                ) {
                    Text(
                        text = transaction.wallet.name,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = iconColor,
                            fontSize = 11.sp,
                        ),
                        maxLines = 1,
                        softWrap = false,
                    )
                }
            }
        }
    }
}

fun Instant.toRelativeDateTimeString(
    now: Instant = Clock.System.now(),
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {
    val time24hFormat = LocalDateTime.Format {
        hour()
        char(':')
        minute()
    }

    val targetDateTime = this.toLocalDateTime(timeZone)
    val currentDateTime = now.toLocalDateTime(timeZone)

    val daysAgo = targetDateTime.date.daysUntil(currentDateTime.date)
    val timeStr = targetDateTime.format(time24hFormat)

    val relativeDay = when (daysAgo) {
        0 -> "Today"
        1 -> "Yesterday"
        in 2..Int.MAX_VALUE -> "$daysAgo days ago"
        else -> "${targetDateTime.date}" // Fallback for future dates
    }

    return "$relativeDay $timeStr"
}

@Preview
@Composable
fun TransactionCardPreview() {
    val now: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())

    FinTrackTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFFF8FAFC))
                .padding(16.dp),
        ) {
            TransactionCard(
                transaction = TransactionWithCategoryAndWallet(
                    transaction = Transaction(
                        id = "1",
                        name = "Point Coffee",
                        amount = 25000f,
                        type = INCOME,
                        categoryId = "TODO()",
                        userId = "TODO()",
                        walletId = "TODO()",
                        createdAt = now
                    ),
                    category = Category(
                        id = "TODO()",
                        name = "Food and Drink",
                        icon = "restaurant"
                    ),
                    wallet = Wallet(
                        id = "TODO()",
                        name = "Jago Main",
                        purpose = "Spending Main",
                        type = WalletType.CARD.name,
                        amount = 15000000.0,
                        number = "1023243545"
                    )
                )
            )
        }
    }
}