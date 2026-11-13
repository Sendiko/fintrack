package id.my.sendiko.fintrack.budget.presentation.detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.budget_active
import fintrack.composeapp.generated.resources.budget_expired
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import org.jetbrains.compose.resources.stringResource

@Composable
fun BudgetStatusChip(
    modifier: Modifier = Modifier,
    isActive: Boolean,
) {
    Surface(
        modifier = modifier,
        color = if (isActive) primaryOrange.copy(alpha = 0.5f) else secondaryBlue.copy(alpha = 0.5f),
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier.clip(CircleShape)
                    .size(16.dp)
                    .background(if (isActive) primaryOrange else secondaryBlue)
            )
            Text(
                text = stringResource(
                    resource = if (isActive) Res.string.budget_active else Res.string.budget_expired
                )
            )
        }
    }
}

@Preview
@Composable
fun BudgetStatusChipPrev() {
    FinTrackTheme {
        Surface {
            BudgetStatusChip(modifier = Modifier.padding(16.dp), isActive = false)
        }
    }
}