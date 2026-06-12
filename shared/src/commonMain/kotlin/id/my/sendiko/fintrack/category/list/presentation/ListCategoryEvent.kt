package id.my.sendiko.fintrack.category.list.presentation

sealed interface ListCategoryEvent {

    data object OnLoadData : ListCategoryEvent

    data class OnNameChanged(val name: String) : ListCategoryEvent

    data class OnShowModalBottomSheet(val id: String, val name: String) : ListCategoryEvent

    data object OnDismissModalBottomSheet : ListCategoryEvent

    data class OnShowDeleteDialog(val id: String) : ListCategoryEvent

    data object OnDismissDeleteDialog : ListCategoryEvent

    data object OnDelete : ListCategoryEvent

    data object OnSave : ListCategoryEvent

}