package id.my.sendiko.fintrack.wallet.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue

@Composable
fun StageBar(
    modifier: Modifier = Modifier,
    stage: Int
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Surface(
            modifier = Modifier.weight(1f)
                .height(4.dp),
            color = if (stage > 0) primaryOrange else secondaryBlue,
            shape = CircleShape
        ) {}
        Surface(
            modifier = Modifier.weight(1f)
                .height(4.dp),
            color = if (stage > 1) primaryOrange else secondaryBlue,
            shape = CircleShape
        ) {}
    }
}