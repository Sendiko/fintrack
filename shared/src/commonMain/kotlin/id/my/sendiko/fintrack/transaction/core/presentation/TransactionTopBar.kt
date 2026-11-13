package id.my.sendiko.fintrack.transaction.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@Composable
fun TransactionTopBar(
    title: String,
    onNavigateBack: () -> Unit,
) {
    MediumTopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.back)
                )
            }
        }
    )
}