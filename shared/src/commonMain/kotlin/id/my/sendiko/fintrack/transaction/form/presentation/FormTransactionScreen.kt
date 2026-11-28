package id.my.sendiko.fintrack.transaction.form.presentation

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.choose_category_label
import fintrack.composeapp.generated.resources.choose_wallet_label
import fintrack.composeapp.generated.resources.confirm
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.create_expense_title
import fintrack.composeapp.generated.resources.create_income_title
import fintrack.composeapp.generated.resources.delete_transaction_body
import fintrack.composeapp.generated.resources.delete_transaction_headline
import fintrack.composeapp.generated.resources.edit_expense_title
import fintrack.composeapp.generated.resources.edit_income_title
import fintrack.composeapp.generated.resources.empty_category
import fintrack.composeapp.generated.resources.select_time_title
import fintrack.composeapp.generated.resources.set_expense_name_hint
import fintrack.composeapp.generated.resources.set_expense_name_label
import fintrack.composeapp.generated.resources.transaction_date_label
import id.my.sendiko.fintrack.category.core.domain.model.Category
import id.my.sendiko.fintrack.category.core.presentation.components.CategoryOptionItem
import id.my.sendiko.fintrack.category.core.presentation.components.CategorySelector
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.icon.CategoryIcon
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.greenApproved
import id.my.sendiko.fintrack.core.presentation.theme.icon
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.redError
import id.my.sendiko.fintrack.core.presentation.theme.secondaryBlue
import id.my.sendiko.fintrack.core.presentation.theme.utilityWhite
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.EXPENSE
import id.my.sendiko.fintrack.transaction.core.domain.model.TransactionType.INCOME
import id.my.sendiko.fintrack.transaction.core.presentation.TransactionTopBar
import id.my.sendiko.fintrack.transaction.form.presentation.components.TransactionAmountCard
import id.my.sendiko.fintrack.transaction.form.presentation.components.TransactionDateSelector
import id.my.sendiko.fintrack.wallet.core.presentation.components.WalletDropdown
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

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

    if (state.showCategoriesBottomSheet) {
        val allCategories = state.categories.map {
            Category(
                id = it.category.id,
                name = it.category.name,
                icon = it.category.icon,
            )
        }
        ModalBottomSheet(
            onDismissRequest = { onEvent(FormTransactionEvent.DismissCategoriesBottomSheet) },
            containerColor = utilityWhite,
            contentColor = secondaryBlue,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.choose_category_label),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (allCategories.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.empty_category),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = icon,
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 24.dp),
                    ) {
                        items(
                            items = allCategories,
                            key = { it.id },
                        ) { category ->
                            val isSelected = category.id == state.selectedCategory?.id
                            CategoryOptionItem(
                                modifier = Modifier.fillMaxWidth(),
                                label = category.name,
                                isSelected = isSelected,
                                onClick = {
                                    onEvent(
                                        FormTransactionEvent.OnCategoryChanged(
                                            category = category,
                                            moveToFirst = true,
                                        )
                                    )
                                },
                                iconContent = {
                                    CategoryIcon(
                                        iconKey = category.icon,
                                        contentDescription = category.name,
                                        tint = if (isSelected) greenApproved else icon,
                                        modifier = Modifier.size(24.dp),
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    if (state.showDatePicker) {
        val initialDateMillis = LocalDateTime(
            date = state.transactionDate.date,
            time = LocalTime(0, 0),
        ).toInstant(TimeZone.UTC).toEpochMilliseconds()
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialDateMillis,
        )
        DatePickerDialog(
            onDismissRequest = { onEvent(FormTransactionEvent.DismissDatePicker) },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val pickedDate = Instant.fromEpochMilliseconds(millis)
                                .toLocalDateTime(TimeZone.UTC)
                                .date
                            onEvent(
                                FormTransactionEvent.OnTransactionDateChanged(
                                    LocalDateTime(
                                        date = pickedDate,
                                        time = state.transactionDate.time,
                                    )
                                )
                            )
                        }
                        onEvent(FormTransactionEvent.DismissDatePicker)
                        onEvent(FormTransactionEvent.ShowTimePicker)
                    }
                ) {
                    Text(stringResource(Res.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onEvent(FormTransactionEvent.DismissDatePicker) }
                ) {
                    Text(stringResource(Res.string.cancel))
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (state.showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = state.transactionDate.hour,
            initialMinute = state.transactionDate.minute,
            is24Hour = true,
        )
        BasicAlertDialog(
            onDismissRequest = { onEvent(FormTransactionEvent.DismissTimePicker) },
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = utilityWhite,
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(Res.string.select_time_title),
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TimePicker(state = timePickerState)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        TextButton(
                            onClick = { onEvent(FormTransactionEvent.DismissTimePicker) }
                        ) {
                            Text(stringResource(Res.string.cancel))
                        }
                        TextButton(
                            onClick = {
                                onEvent(
                                    FormTransactionEvent.OnTransactionDateChanged(
                                        LocalDateTime(
                                            date = state.transactionDate.date,
                                            time = LocalTime(
                                                hour = timePickerState.hour,
                                                minute = timePickerState.minute,
                                            ),
                                        )
                                    )
                                )
                                onEvent(FormTransactionEvent.DismissTimePicker)
                            }
                        ) {
                            Text(stringResource(Res.string.confirm))
                        }
                    }
                }
            }
        }
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
                            onNavigateBack()
                        }
                    )
                },
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
                                onEvent(FormTransactionEvent.OnSave)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryOrange,
                            )
                        ) {
                            Text(
                                text = stringResource(Res.string.create),
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        TransactionAmountCard(
                            type = state.selectedType,
                            amount = state.amount,
                            onAmountChange = { amount ->
                                onEvent(FormTransactionEvent.OnAmountChanged(amount))
                            }
                        )
                    }
                    item {
                        Text(
                            text = stringResource(Res.string.set_expense_name_label),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.name,
                            onValueChange = { onEvent(FormTransactionEvent.OnNameChanged(it)) },
                            hint = stringResource(Res.string.set_expense_name_hint),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Title,
                                    contentDescription = stringResource(Res.string.set_expense_name_label)
                                )
                            },
                            trailingIcon = {
                                if (state.name.isNotBlank()) {
                                    IconButton(
                                        onClick = { onEvent(FormTransactionEvent.OnNameChanged("")) }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        )
                    }
                    item {
                        Text(
                            text = stringResource(Res.string.choose_wallet_label),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        WalletDropdown(
                            wallet = state.selectedWallet,
                            wallets = state.wallets,
                            onWalletSelected = { onEvent(FormTransactionEvent.OnWalletChanged(it)) }
                        )
                    }
                    item {
                        Text(
                            text = stringResource(Res.string.choose_category_label),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        CategorySelector(
                            categories = state.categories.map {
                                Category(
                                    id = it.category.id,
                                    name = it.category.name,
                                    icon = it.category.icon,
                                )
                            },
                            selectedCategory = state.selectedCategory,
                            onCategorySelected = {
                                onEvent(FormTransactionEvent.OnCategoryChanged(it))
                            },
                            onMoreCategories = {
                                onEvent(FormTransactionEvent.ShowCategoriesBottomSheet)
                            }
                        )
                    }
                    item {
                        Text(
                            text = stringResource(Res.string.transaction_date_label),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        TransactionDateSelector(
                            dateTime = state.transactionDate,
                            onClick = { onEvent(FormTransactionEvent.ShowDatePicker) },
                        )
                    }
                }
            }
        }
    )

}

@Preview
@Composable
fun FormTransactionScreenPrev() {
    FinTrackTheme {
        FormTransactionScreen(
            state = FormTransactionState(
                name = "",
                amount = 50000.0
            ),
            onEvent = {  },
            onNavigateBack = {  }
        )
    }
}