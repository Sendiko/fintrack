package id.my.sendiko.fintrack.transaction.create.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.choose_category_hint
import fintrack.composeapp.generated.resources.choose_category_label
import fintrack.composeapp.generated.resources.choose_wallet_hint
import fintrack.composeapp.generated.resources.choose_wallet_label
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.create_income_title
import fintrack.composeapp.generated.resources.next
import fintrack.composeapp.generated.resources.set_amount_label
import fintrack.composeapp.generated.resources.set_income_name_hint
import fintrack.composeapp.generated.resources.set_income_name_label
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.numerickeyboard.NumericKeyboard
import id.my.sendiko.fintrack.core.presentation.rupiah.toRupiah
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.core.presentation.textfields.DropdownMenu
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import id.my.sendiko.fintrack.wallet.core.presentation.CreateTransactionTopBar
import id.my.sendiko.fintrack.wallet.core.presentation.StageBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreateTransactionScreen(
    state: CreateTransactionState,
    onEvent: (CreateTransactionEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {

    LaunchedEffect(true) {
        onEvent(CreateTransactionEvent.LoadData)
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
        content = {
            Scaffold(
                containerColor = utilityWhite,
                contentColor = secondaryBlue,
                bottomBar = {
                    Button(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        onClick = {
                            if (state.stage == 1) {
                                onEvent(CreateTransactionEvent.OnNext)
                            } else {
                                onEvent(CreateTransactionEvent.OnCreate)
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
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CreateTransactionTopBar(
                        title = stringResource(Res.string.create_income_title),
                        onNavigateBack = { onNavigateBack() }
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
                                            onChosen = {
                                                onEvent(
                                                    CreateTransactionEvent
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
                                            items = state.categories,
                                            hint = stringResource(Res.string.choose_category_hint),
                                            onChosen = {
                                                onEvent(
                                                    CreateTransactionEvent
                                                        .OnCategoryChanged(it)
                                                )
                                            }
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.set_income_name_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.name,
                                            onValueChange = {
                                                onEvent(
                                                    CreateTransactionEvent
                                                        .OnNameChanged(it)
                                                )
                                            },
                                            hint = stringResource(Res.string.set_income_name_hint),
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
                                            onEvent(CreateTransactionEvent.OnNumberPressed(it))
                                        },
                                        onBackspace = {
                                            onEvent(CreateTransactionEvent.OnBackspace)
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