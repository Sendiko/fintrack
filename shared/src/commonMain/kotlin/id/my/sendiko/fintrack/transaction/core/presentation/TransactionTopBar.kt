package id.my.sendiko.fintrack.transaction.core.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import id.my.sendiko.fintrack.core.presentation.buttons.RoundedIconButton
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.icon
import id.my.sendiko.fintrack.core.presentation.theme.surface
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransactionTopBar(
    title: String,
    onNavigateBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = utilityWhite,
            actionIconContentColor = icon
        ),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            RoundedIconButton(
                icon = Icons.AutoMirrored.Default.ArrowBack,
                onClick = onNavigateBack,
                contentDescription = stringResource(Res.string.back)
            )
        },
        actions = actions
    )
}

@Preview
@Composable
fun TransactionTopBarPreview() {
    FinTrackTheme {
        TransactionTopBar(
            title = "Pengeluaran",
            onNavigateBack = { },
            actions = {
                RoundedIconButton(
                    icon = Icons.Default.Refresh,
                    onClick = {  }
                )
                RoundedIconButton(
                    icon = Icons.Default.Info,
                    onClick = {  }
                )
            }
        )
    }
}