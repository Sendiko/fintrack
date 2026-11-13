package id.my.sendiko.fintrack.budget.presentation.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.budget_label
import fintrack.composeapp.generated.resources.form_budget_title
import id.my.sendiko.fintrack.budget.presentation.list.presentation.components.BudgetCard
import id.my.sendiko.fintrack.core.navigation.DetailBudgetDestination
import id.my.sendiko.fintrack.core.navigation.FormBudgetDestination
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListBudgetScreen(
    state: ListBudgetState,
    onNavigate: (Any?) -> Unit
) {

    NotificationBox(
        message = state.message.asComposableString(),
        isLoading = state.isLoading,
        content = {
            Scaffold(
                containerColor = utilityWhite,
                topBar = {
                    MediumTopAppBar(
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
                            contentDescription = stringResource(Res.string.form_budget_title)
                        )
                    }
                }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = it.calculateTopPadding() + 16.dp,
                        bottom = it.calculateBottomPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                ) {
                    items(state.budgets) { budget ->
                        BudgetCard(
                            modifier = Modifier.clickable {
                                onNavigate(DetailBudgetDestination(budget.id))
                            },
                            budget = budget
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ListBudgetScreenPrev() {
    FinTrackTheme {
        ListBudgetScreen(
            state = ListBudgetState(isLoading = false
            ),
            onNavigate = {}
        )
    }
}