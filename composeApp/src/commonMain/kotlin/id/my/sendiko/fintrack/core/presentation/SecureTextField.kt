package id.my.sendiko.fintrack.core.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.primaryOrange
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.password_not_visible
import fintrack.composeapp.generated.resources.password_visible
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecureTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isVisible: Boolean = true,
    onVisibilityChanged: (Boolean) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (isVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = primaryOrange,
            focusedBorderColor = primaryOrange
        ),
        placeholder = {
            Text(text = hint)
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}