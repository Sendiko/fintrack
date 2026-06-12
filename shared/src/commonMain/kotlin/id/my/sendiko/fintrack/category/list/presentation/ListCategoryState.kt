package id.my.sendiko.fintrack.category.list.presentation

import id.my.sendiko.fintrack.category.core.domain.model.CategoryWithTransactions

data class ListCategoryState(
    val token: String = "",
    val isLoading: Boolean = false,
    val message: String = "",
    val categoryId: String = "",
    val showModalBottomSheet: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val categoryName: String = "",
    val categories: List<CategoryWithTransactions> = emptyList()
)
