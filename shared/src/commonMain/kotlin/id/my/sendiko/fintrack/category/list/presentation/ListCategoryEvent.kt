package id.my.sendiko.fintrack.category.list.presentation

sealed interface ListCategoryEvent {

    data object OnLoadData : ListCategoryEvent

    data class OnNameChanged(val name: String) : ListCategoryEvent

    data class OnShowModalBottomSheet(val id: String) : ListCategoryEvent

    data object OnDismissModalBottomSheet : ListCategoryEvent

    data object OnShowDeleteDialog : ListCategoryEvent

    data object OnDismissDeleteDialog : ListCategoryEvent

    data object OnSave : ListCategoryEvent

}