package id.my.sendiko.fintrack.core.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    modifier: Modifier = Modifier,
    items: List<T>,
    onChosen: (T) -> Unit,
    hint: String
) {
    var isExpanding by rememberSaveable { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanding,
        onExpandedChange = { isExpanding = it },
        content = {

            DropdownMenuField(
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                value = text,
                onValueChange = {  },
                hint = hint,
                expanded = isExpanding
            )

            ExposedDropdownMenu(
                expanded = isExpanding,
                onDismissRequest = { isExpanding = !isExpanding },
            ) {
                items.forEach { it ->
                    DropdownMenuItem(
                        text = { Text(text = it.toString()) },
                        onClick = {
                            onChosen(it)
                            text = it.toString()
                                  },
                    )
                }
            }
        }
    )
}