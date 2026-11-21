package id.my.sendiko.fintrack.core.presentation.textfields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.core.presentation.theme.FinTrackTheme
import id.my.sendiko.fintrack.core.presentation.theme.icon
import id.my.sendiko.fintrack.core.presentation.theme.surface

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    supportingText: String? = null,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        readOnly = readOnly,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        keyboardActions = KeyboardActions(
            onAny = { onImeAction() }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = surface,
            focusedContainerColor = surface,
            unfocusedBorderColor = surface,
            focusedBorderColor = icon.copy(alpha = 0.6f),
            unfocusedTrailingIconColor = icon.copy(alpha = 0.6f),
            focusedTrailingIconColor = icon,
            unfocusedLeadingIconColor = icon.copy(alpha = 0.6f),
            focusedLeadingIconColor = icon
        ),
        placeholder = {
            Text(text = hint)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = if (supportingText.isNullOrBlank()) null else {
            { Text(text = supportingText) }
        }
    )
}

@Preview
@Composable
fun BaseTextFieldPrev() {
    FinTrackTheme {
        Surface {
            BaseTextField(
                modifier = Modifier.padding(16.dp),
                value = "",
                onValueChange = {  },
                hint = "This is a hint.",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = " "
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = ""
                    )
                }
            )
        }
    }
}