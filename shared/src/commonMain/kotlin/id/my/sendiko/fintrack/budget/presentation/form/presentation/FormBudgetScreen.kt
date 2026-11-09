package id.my.sendiko.fintrack.budget.presentation.form.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.cancel
import fintrack.composeapp.generated.resources.confirm
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.numerickeyboard.NumericKeyboard
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
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
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                topBar = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = onNavigateBack,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "Buat anggaran baru",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                            text = "Mulai dari",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.startDate,
                            onValueChange = { onEvent(FormBudgetEvent.OnStartDateConfirmed(it)) },
                            hint = "yyyy-mm-dd",
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onEvent(FormBudgetEvent.OnStartDatePickerClicked(true))
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Pilih Tanggal Mulai"
                                    )
                                }
                            },
                            outlineColor = secondaryBlue
                        )
                    }

                    Column {
                        Text(
                            text = "Sampai",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        BaseTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.endDate,
                            onValueChange = { onEvent(FormBudgetEvent.OnEndDateConfirmed(it)) },
                            hint = "yyyy-mm-dd",
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        onEvent(FormBudgetEvent.OnEndDatePickerClicked(true))
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Pilih Tanggal Selesai"
                                    )
                                }
                            },
                            outlineColor = secondaryBlue
                        )
                    }
                    Text(
                        text = "Anggaran",
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
                            text = "Simpan",
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
