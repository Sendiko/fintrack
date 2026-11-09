package id.my.sendiko.fintrack.budget.presentation.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.add_wallets
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.budget_label
import id.my.sendiko.fintrack.core.navigation.FormBudgetDestination
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListBudgetScreen(
    onNavigate: (Any?) -> Unit
) {
    Scaffold(
        containerColor = utilityWhite,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.budget_label)) },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigate(null) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate(FormBudgetDestination) },
                containerColor = primaryOrange,
                contentColor = utilityWhite
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.add_wallets)
                )
            }
        }
    ) {

    }
}

@Preview
@Composable
fun ListBudgetScreenPrev() {
    FinTrackTheme {
        ListBudgetScreen(onNavigate = {})
    }
}