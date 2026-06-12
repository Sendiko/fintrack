package id.my.sendiko.fintrack.category.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.back
import fintrack.composeapp.generated.resources.category_name_hint
import fintrack.composeapp.generated.resources.category_name_label
import fintrack.composeapp.generated.resources.create
import fintrack.composeapp.generated.resources.empty_category
import fintrack.composeapp.generated.resources.list_category_title
import id.my.sendiko.fintrack.category.list.presentation.components.CategoryCard
import id.my.sendiko.fintrack.core.presentation.NotificationBox
import id.my.sendiko.fintrack.core.presentation.textfields.BaseTextField
import id.my.sendiko.fintrack.theme.primaryOrange
import id.my.sendiko.fintrack.theme.utilityWhite
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCategoryScreen(
    state: ListCategoryState,
    onEvent: (ListCategoryEvent) -> Unit,
    onNavigateBack: () -> Unit
) {

    LaunchedEffect(true) {
        onEvent(ListCategoryEvent.OnLoadData)
    }

    if (state.showModalBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(ListCategoryEvent.OnDismissModalBottomSheet) },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.category_name_label),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(4.dp))
                BaseTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.categoryName,
                    onValueChange = { onEvent(ListCategoryEvent.OnNameChanged(it)) },
                    hint = stringResource(Res.string.category_name_hint)
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    onClick = { }
                ) {
                    Text(stringResource(Res.string.create))
                }
            }
        }
    }

    NotificationBox(
        message = state.message,
        isLoading = state.isLoading,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.list_category_title),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onNavigateBack() },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onEvent(ListCategoryEvent.OnShowModalBottomSheet("")) },
                    containerColor = primaryOrange,
                    contentColor = utilityWhite
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        ) { paddingValues ->
            if (state.categories.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.empty_category),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(state.categories) { category ->
                        CategoryCard(
                            modifier = Modifier.fillMaxWidth(),
                            category = category,
                            onEdit = { onEvent(ListCategoryEvent.OnShowModalBottomSheet(category.category.id)) },
                            onDelete = { }
                        )
                    }
                }
            }
        }
    }

}