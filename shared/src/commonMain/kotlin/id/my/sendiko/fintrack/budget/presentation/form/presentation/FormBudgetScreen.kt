package id.my.sendiko.fintrack.budget.presentation.form.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.budget_label
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.confirm
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.date_hint
import fintrack.composeapp.generated.resources.end_form_label
import fintrack.composeapp.generated.resources.form_budget_title
import fintrack.composeapp.generated.resources.select_end_date
import fintrack.composeapp.generated.resources.select_start_date
import fintrack.composeapp.generated.resources.start_form_label
import id.my.sendiko.fintrack.budget.presentation.core.util.formatDate
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.numerickeyboard.NumericKeyboard
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Instant

@Composable
fun FormBudgetScreen(
    state: FormBudgetState,
    onEvent: (FormBudgetEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    if (state.isStartDatePickerOpen) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {
                onEvent(FormBudgetEvent.OnStartDatePickerClicked(false))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formattedDate = formatMillisToDate(millis)
                            onEvent(FormBudgetEvent.OnStartDateConfirmed(formattedDate))
                        }
                        onEvent(FormBudgetEvent.OnStartDatePickerClicked(false))
                    }
                ) {
                    Text(stringResource(Res.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onEvent(FormBudgetEvent.OnStartDatePickerClicked(false))
                    }
                ) {
                    Text(stringResource(Res.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (state.isEndDatePickerOpen) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = {
                onEvent(FormBudgetEvent.OnEndDatePickerClicked(false))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formattedDate = formatMillisToDate(millis)
                            onEvent(FormBudgetEvent.OnEndDateConfirmed(formattedDate))
                        }
                        onEvent(FormBudgetEvent.OnEndDatePickerClicked(false))
                    }
                ) {
                    Text(stringResource(Res.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onEvent(FormBudgetEvent.OnEndDatePickerClicked(false))
                    }
                ) {
                    Text(stringResource(Res.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
        content = {
            Scaffold(
                modifier = Modifier.systemBarsPadding(),
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                topBar = {
                    MediumTopAppBar(
                        title = {
                            Text(stringResource(Res.string.form_budget_title))
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onNavigateBack
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = stringResource(Res.string.back)
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(Res.string.start_form_label),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = if (state.startDate.isBlank())
                                "" else state.startDate.formatDate(),
                            onValueChange = { onEvent(FormBudgetEvent.OnStartDateConfirmed(it)) },
                            hint = stringResource(Res.string.date_hint).formatDate(),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onEvent(FormBudgetEvent.OnStartDatePickerClicked(true))
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = stringResource(Res.string.select_start_date)
                                    )
                                }
                            },
                            outlineColor = secondaryBlue
                        )
                    }

                    Column {
                        Text(
                            text = stringResource(Res.string.end_form_label),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = if (state.endDate.isBlank())
                                "" else state.endDate.formatDate(),
                            onValueChange = { onEvent(FormBudgetEvent.OnEndDateConfirmed(it)) },
                            hint = stringResource(Res.string.date_hint).formatDate(),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onEvent(FormBudgetEvent.OnEndDatePickerClicked(true))
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = stringResource(Res.string.select_end_date)
                                    )
                                }
                            },
                            outlineColor = secondaryBlue
                        )
                    }
                    Text(
                        text = stringResource(Res.string.budget_label),
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
                            onEvent(FormBudgetEvent.OnNumberPressed(it))
                        },
                        onBackspace = {
                            onEvent(FormBudgetEvent.OnBackspace)
                        }
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = {
                            onEvent(FormBudgetEvent.OnSave)
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
                }
            }
        }
    )
}

@Preview
@Composable
fun FormBudgetScreenPrev() {
    FinTrackTheme {
        FormBudgetScreen(
            state = FormBudgetState(),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}

private fun formatMillisToDate(millis: Long): String {
    val instant = Instant.fromEpochMilliseconds(millis)
    val date = instant.toLocalDateTime(TimeZone.UTC).date
    return date.toString()
}
