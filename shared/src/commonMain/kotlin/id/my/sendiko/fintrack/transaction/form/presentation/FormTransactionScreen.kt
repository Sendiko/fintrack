package id.my.sendiko.fintrack.transaction.form.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.choose_category_hint
import fintrack.composeapp.generated.resources.choose_category_label
import fintrack.composeapp.generated.resources.choose_wallet_hint
import fintrack.composeapp.generated.resources.choose_wallet_label
import fintrack.composeapp.generated.resources.confirm
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.create_expense_title
import fintrack.composeapp.generated.resources.create_income_title
import fintrack.composeapp.generated.resources.delete_transaction_body
import fintrack.composeapp.generated.resources.delete_transaction_headline
import fintrack.composeapp.generated.resources.edit_expense_title
import fintrack.composeapp.generated.resources.edit_income_title
import fintrack.composeapp.generated.resources.next
import fintrack.composeapp.generated.resources.set_amount_label
import fintrack.composeapp.generated.resources.set_expense_name_hint
import fintrack.composeapp.generated.resources.set_expense_name_label
import fintrack.composeapp.generated.resources.set_income_name_hint
import fintrack.composeapp.generated.resources.set_income_name_label
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.StageBar
import id.my.sendiko.fintrack.core.presentation.numerickeyboard.NumericKeyboard
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.core.presentation.textfields.DropdownMenu
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.redError
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.EXPENSE
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.INCOME
import id.my.sendiko.fintrack.transaction.core.presentation.TransactionTopBar
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTransactionScreen(
    state: FormTransactionState,
    onEvent: (FormTransactionEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {

    LaunchedEffect(true) {
        onEvent(FormTransactionEvent.LoadData)
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            delay(2.seconds)
            onNavigateBack()
        }
    }

    if (state.showDeleteDialog) {
        BasicAlertDialog(
            onDismissRequest = { onEvent(FormTransactionEvent.DismissDeleteDialog) },
            content = {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.delete_transaction_headline),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = stringResource(Res.string.delete_transaction_body),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                shape = RoundedCornerShape(
                                    topEnd = 4.dp,
                                    topStart = 8.dp,
                                    bottomEnd = 4.dp,
                                    bottomStart = 8.dp
                                ),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = secondaryBlue,
                                ),
                                border = BorderStroke(1.dp, secondaryBlue),
                                onClick = { onEvent(FormTransactionEvent.DismissDeleteDialog) },
                                modifier = Modifier.weight(2f),
                            ) {
                                Text(stringResource(Res.string.cancel))
                            }
                            Spacer(Modifier.width(2.dp))
                            Button(
                                modifier = Modifier.weight(3f),
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 8.dp,
                                    bottomStart = 4.dp,
                                    bottomEnd = 8.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = redError,
                                    contentColor = utilityWhite
                                ),
                                onClick = { onEvent(FormTransactionEvent.OnDelete) }
                            ) {
                                Text(stringResource(Res.string.confirm))
                            }
                        }
                    }
                }
            }
        )
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
        content = {
            Scaffold(
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                bottomBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                            .height(IntrinsicSize.Min)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            shape = if (state.transactionId.isBlank())
                                RoundedCornerShape(16.dp)
                            else RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 4.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 4.dp
                            ),
                            contentPadding = PaddingValues(vertical = 16.dp),
                            onClick = {
                                if (state.stage == 1) {
                                    onEvent(FormTransactionEvent.OnNext)
                                } else {
                                    onEvent(FormTransactionEvent.OnSave)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryOrange,
                            )
                        ) {
                            Text(
                                text = when (state.stage) {
                                    1 -> stringResource(Res.string.next)
                                    2 -> stringResource(Res.string.create)
                                    else -> ""
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (state.transactionId.isNotBlank()) {
                            Spacer(Modifier.width(4.dp))
                            IconButton(
                                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = redError,
                                    contentColor = utilityWhite
                                ),
                                shape = RoundedCornerShape(
                                    topStart = 4.dp,
                                    topEnd = 16.dp,
                                    bottomStart = 4.dp,
                                    bottomEnd = 16.dp
                                ),
                                onClick = { onEvent(FormTransactionEvent.ShowDeleteDialog) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TransactionTopBar(
                        title = if (state.transactionId.isNotBlank()) {
                            when (state.selectedType) {
                                INCOME -> stringResource(Res.string.edit_income_title)
                                EXPENSE -> stringResource(Res.string.edit_expense_title)
                            }
                        } else {
                            when (state.selectedType) {
                                INCOME -> stringResource(Res.string.create_income_title)
                                EXPENSE -> stringResource(Res.string.create_expense_title)
                            }
                        },
                        onNavigateBack = {
                            if (state.stage == 2)
                                onEvent(FormTransactionEvent.OnPrevious)
                            else onNavigateBack()
                        }
                    )
                    AnimatedContent(
                        targetState = state.stage,
                        transitionSpec = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                                .togetherWith(slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left))
                        }
                    ) { pageStage ->
                        StageBar(
                            stage = state.stage
                        )
                        when (pageStage) {
                            1 -> {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    item {
                                        Text(
                                            text = stringResource(Res.string.choose_wallet_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        DropdownMenu(
                                            modifier = Modifier.fillMaxWidth(),
                                            items = state.wallets,
                                            hint = stringResource(Res.string.choose_wallet_hint),
                                            initialValue = state.selectedWallet?.name ?: "",
                                            onChosen = {
                                                onEvent(
                                                    FormTransactionEvent
                                                        .OnWalletChanged(it)
                                                )
                                            }
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.choose_category_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        DropdownMenu(
                                            modifier = Modifier.fillMaxWidth(),
                                            items = state.categories.map { it.category },
                                            hint = stringResource(Res.string.choose_category_hint),
                                            initialValue = state.selectedCategory?.name ?: "",
                                            onChosen = {
                                                onEvent(
                                                    FormTransactionEvent
                                                        .OnCategoryChanged(it)
                                                )
                                            }
                                        )
                                    }
                                    item {
                                        Text(
                                            text = when (state.selectedType) {
                                                INCOME -> stringResource(Res.string.set_income_name_label)
                                                EXPENSE -> stringResource(Res.string.set_expense_name_label)
                                            },
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.name,
                                            onValueChange = {
                                                onEvent(
                                                    FormTransactionEvent
                                                        .OnNameChanged(it)
                                                )
                                            },
                                            hint = when (state.selectedType) {
                                                INCOME -> stringResource(Res.string.set_income_name_hint)
                                                EXPENSE -> stringResource(Res.string.set_expense_name_hint)
                                            },
                                            outlineColor = secondaryBlue
                                        )
                                    }
                                }
                            }

                            2 -> {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .padding(top = 16.dp)
                                        .fillMaxSize()
                                ) {
                                    Text(
                                        text = stringResource(Res.string.set_amount_label),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = (state.amount.toDoubleOrNull() ?: 0.0).toRupiah(),
                                        style = MaterialTheme.typography.headlineLarge,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        color = secondaryBlue
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    NumericKeyboard(
                                        onClick = {
                                            onEvent(FormTransactionEvent.OnNumberPressed(it))
                                        },
                                        onBackspace = {
                                            onEvent(FormTransactionEvent.OnBackspace)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )

}