package id.my.sendiko.fintrack.wallet.create.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.create_wallet_title
import fintrack.composeapp.generated.resources.initial_balance
import fintrack.composeapp.generated.resources.next
import fintrack.composeapp.generated.resources.wallet_name_hint
import fintrack.composeapp.generated.resources.wallet_name_label
import fintrack.composeapp.generated.resources.wallet_purpose_hint
import fintrack.composeapp.generated.resources.wallet_purpose_label
import fintrack.composeapp.generated.resources.wallet_type_hint
import fintrack.composeapp.generated.resources.wallet_type_label
import id.my.sendiko.fintrack.core.presentation.BaseTextField
import id.my.sendiko.fintrack.core.presentation.DropdownMenu
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.dashboard.presentation.toRupiah
import id.my.sendiko.fintrack.theme.FinTrackTheme
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateWalletScreen(
    state: CreateWalletState,
    onEvent: (CreateWalletEvent) -> Unit,
    onNavigate: (Any?) -> Unit,
) {
    NotificationBox(
        message = "",
        isLoading = false,
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
                                onEvent(CreateWalletEvent.OnNext)
                            } else {
                                onEvent(CreateWalletEvent.OnCreate)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryOrange,
                        )
                    ) {
                        Text(
                            text = when (state.stage) {
                                1 -> stringResource(Res.string.next)
                                2 -> stringResource(Res.string.next)
                                else -> ""
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = stringResource(Res.string.create_wallet_title),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    AnimatedContent(
                        targetState = state.stage,
                        transitionSpec = {
                            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
                                .togetherWith(slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left))
                        }
                    ) { pageStage ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Surface(
                                modifier = Modifier.weight(1f)
                                    .height(4.dp),
                                color = if (state.stage > 0) primaryOrange else secondaryBlue,
                                shape = CircleShape
                            ) {}
                            Surface(
                                modifier = Modifier.weight(1f)
                                    .height(4.dp),
                                color = if (state.stage > 1) primaryOrange else secondaryBlue,
                                shape = CircleShape
                            ) {}
                        }
                        when(pageStage) {
                            1 -> {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    item {
                                        Column(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            IconButton(
                                                onClick = { /*TODO*/ },
                                            ) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                                    contentDescription = stringResource(Res.string.back)
                                                )
                                            }
                                            Text(
                                                modifier = Modifier.padding(start = 16.dp),
                                                text = stringResource(Res.string.create_wallet_title),
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_name_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.name,
                                            onValueChange = {  },
                                            hint = stringResource(Res.string.wallet_name_hint),
                                            outlineColor = secondaryBlue
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_type_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        DropdownMenu(
                                            modifier = Modifier.fillMaxWidth(),
                                            items = state.walletTypeList,
                                            onChosen = {  },
                                            hint = stringResource(Res.string.wallet_type_hint)
                                        )
                                    }
                                    item {
                                        Text(
                                            text = stringResource(Res.string.wallet_purpose_label),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        BaseTextField(
                                            modifier = Modifier.fillMaxWidth(),
                                            value = state.purpose,
                                            onValueChange = {  },
                                            hint = stringResource(Res.string.wallet_purpose_hint),
                                            outlineColor = secondaryBlue
                                        )
                                    }
                                }
                            }
                            2 -> {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .padding(top = 16.dp)
                                ) {
                                    Text(
                                        text = stringResource(Res.string.initial_balance),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = state.number.toRupiah(),
                                        style = MaterialTheme.typography.headlineLarge,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
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

@Preview
@Composable
private fun CreateWalletScreenPreview() {
    FinTrackTheme {
        CreateWalletScreen(
            state = CreateWalletState(
                stage = 2
            ),
            onEvent = {  },
            onNavigate = {  }
        )
    }
}