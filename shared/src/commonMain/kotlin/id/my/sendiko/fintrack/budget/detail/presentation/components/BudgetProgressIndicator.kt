package id.my.sendiko.fintrack.budget.detail.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.getColorAtPercentage

@Composable
fun BudgetProgressIndicator(
    modifier: Modifier = Modifier,
    totalPercentageUsed: Int,
) {
    LinearProgressIndicator(
        modifier = modifier
            .height(8.dp),
        progress = {
            totalPercentageUsed.toFloat() / 100
        },
        trackColor = getColorAtPercentage(totalPercentageUsed).copy(alpha = 0.5f),
        color = getColorAtPercentage(totalPercentageUsed),
        gapSize = 2.dp,
    )
}

@Preview
@Composable
fun BudgetProgressIndicatorPrev() {
    Surface {
        BudgetProgressIndicator(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            totalPercentageUsed = 100 - 95
        )
    }
}