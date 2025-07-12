package id.my.sendiko.fintrack.core.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.secondaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = secondaryBlue,
            focusedBorderColor = secondaryBlue
        ),
        placeholder = {
            Text(text = hint)
        },
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
        }
    )
}