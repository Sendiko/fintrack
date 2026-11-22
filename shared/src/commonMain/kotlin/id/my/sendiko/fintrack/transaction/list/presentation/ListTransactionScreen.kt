package id.my.sendiko.fintrack.transaction.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.all_label
import fintrack.composeapp.generated.resources.empty_transaction
import fintrack.composeapp.generated.resources.list_transaction_title
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.redError
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType
import id.my.sendiko.fintrack.transaction.core.presentation.TransactionListItem
import id.my.sendiko.fintrack.transaction.core.presentation.TransactionTopBar
import id.my.sendiko.fintrack.transaction.presentation.components.TransactionCard
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListTransactionScreen(
    state: ListTransactionState,
    onEvent: (ListTransactionEvent) -> Unit,
    onNavigate: (Any?) -> Unit
) {

    LaunchedEffect(true) {
        onEvent(ListTransactionEvent.LoadData)
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
        content = {
            Scaffold(
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                topBar = {
                    TransactionTopBar(
                        title = stringResource(Res.string.list_transaction_title),
                        onNavigateBack = { onNavigate(null) }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues),
                ) {
                    SingleChoiceSegmentedButtonRow(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    ) {
                        SegmentedButton(
                            selected = state.selectedType == null,
                            onClick = { onEvent(ListTransactionEvent.OnTypeChanged(null)) },
                            label = { Text(stringResource(Res.string.all_label)) },
                            shape = SegmentedButtonDefaults.itemShape(0, 3),
                            colors = SegmentedButtonDefaults.colors(
                                activeBorderColor = secondaryBlue,
                                activeContainerColor = secondaryBlue,
                                activeContentColor = utilityWhite
                            )
                        )
                        SegmentedButton(
                            selected = state.selectedType == TransactionType.EXPENSE,
                            onClick = { onEvent(ListTransactionEvent.OnTypeChanged(TransactionType.EXPENSE)) },
                            label = { Text(TransactionType.EXPENSE.name) },
                            shape = SegmentedButtonDefaults.itemShape(1, 3),
                            colors = SegmentedButtonDefaults.colors(
                                activeBorderColor = redError,
                                activeContainerColor = redError,
                                activeContentColor = utilityWhite
                            )
                        )
                        SegmentedButton(
                            selected = state.selectedType == TransactionType.INCOME,
                            onClick = { onEvent(ListTransactionEvent.OnTypeChanged(TransactionType.INCOME)) },
                            label = { Text(TransactionType.INCOME.name) },
                            shape = SegmentedButtonDefaults.itemShape(2, 3),
                            colors = SegmentedButtonDefaults.colors(
                                activeBorderColor = greenApproved,
                                activeContainerColor = greenApproved,
                                activeContentColor = utilityWhite
                            )
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.transactions) { transaction ->
                            TransactionCard(
                                transaction = transaction
                            )
                        }
                        if (state.transactions.isEmpty()) {
                            item {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(Res.string.empty_transaction),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    )

}