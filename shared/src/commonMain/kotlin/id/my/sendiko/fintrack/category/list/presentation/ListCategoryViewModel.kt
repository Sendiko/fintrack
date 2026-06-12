package id.my.sendiko.fintrack.category.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListCategoryViewModel(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ListCategoryState())
    val state = _state.asStateFlow()

    fun onEvent(event: ListCategoryEvent) {
        when (event) {
            ListCategoryEvent.OnLoadData -> loadData()
            is ListCategoryEvent.OnNameChanged -> changeName(event.name)
            ListCategoryEvent.OnDismissDeleteDialog -> dismissDeleteDialog()
            ListCategoryEvent.OnShowDeleteDialog -> showDeleteDialog()
            ListCategoryEvent.OnDismissModalBottomSheet -> dismissModalBottomSheet()
            is ListCategoryEvent.OnShowModalBottomSheet -> showModalBottomSheet()
            ListCategoryEvent.OnSave -> saveCategory()
        }
    }

    private fun changeName(name: String) {
        _state.update { it.copy(categoryName = name) }
    }

    private fun setLoading(loading: Boolean) {
        _state.update { it.copy(isLoading = loading) }
    }

    private fun loadData() {
        viewModelScope.launch {
            setLoading(true)
            repository.getCategories()
                .onSuccess { result ->
                    _state.update { it.copy(categories = result) }
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString()) }
                }
            setLoading(false)
        }
    }

    private fun saveCategory() {
        viewModelScope.launch {
            TODO("Implement Create and Update Category")
        }
    }

    private fun showModalBottomSheet() {
        _state.update { it.copy(showModalBottomSheet = true) }
    }

    private fun dismissModalBottomSheet() {
        _state.update { it.copy(showModalBottomSheet = false, categoryName = "") }
    }

    private fun showDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = true) }
    }

    private fun dismissDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

}