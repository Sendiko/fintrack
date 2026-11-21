package id.my.sendiko.fintrack.core.presentation.textfields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.password_label
import fintrack.composeapp.generated.resources.password_not_visible
import fintrack.composeapp.generated.resources.password_visible
import id.my.sendiko.fintrack.core.presentation.theme.icon
import id.my.sendiko.fintrack.core.presentation.theme.primaryOrange
import id.my.sendiko.fintrack.core.presentation.theme.surface
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecureTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isVisible: Boolean = true,
    onVisibilityChanged: (Boolean) -> Unit,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onAny = { onImeAction() }
        ),
        visualTransformation = if (isVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
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
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Password,
                contentDescription = stringResource(Res.string.password_label)
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { onVisibilityChanged(!isVisible) }
            ) {
                Icon(
                    imageVector = if (isVisible)
                        Icons.Default.Visibility
                    else Icons.Default.VisibilityOff,
                    contentDescription = if (isVisible)
                        stringResource(Res.string.password_visible)
                    else stringResource(Res.string.password_not_visible)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        )
    )
}