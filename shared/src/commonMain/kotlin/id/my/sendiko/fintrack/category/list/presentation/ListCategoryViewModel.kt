package id.my.sendiko.fintrack.category.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.sendiko.fintrack.category.core.domain.CategoryRepository
import id.my.sendiko.fintrack.core.network.utils.asUiText
import id.my.sendiko.fintrack.core.network.utils.onError
import id.my.sendiko.fintrack.core.network.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListCategoryViewModel(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(ListCategoryState())
    val state = combine(_state, _userId) { state, userId ->
        state.copy(userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ListCategoryState())

    fun onEvent(event: ListCategoryEvent) {
        when (event) {
            ListCategoryEvent.OnLoadData -> loadData()
            is ListCategoryEvent.OnNameChanged -> changeName(event.name)
            ListCategoryEvent.OnDismissDeleteDialog -> dismissDeleteDialog()
            is ListCategoryEvent.OnShowDeleteDialog -> showDeleteDialog(event.id)
            ListCategoryEvent.OnDismissModalBottomSheet -> dismissModalBottomSheet()
            is ListCategoryEvent.OnShowModalBottomSheet -> showModalBottomSheet(
                event.id,
                event.name
            )

            ListCategoryEvent.OnSave -> saveCategory()
            ListCategoryEvent.OnDelete -> deleteCategory()
        }
    }

    private fun deleteCategory() {
        viewModelScope.launch {
            setLoading(true)
            repository.deleteCategory(state.value.categoryId)
                .onSuccess { result ->
                    _state.update { it.copy(message = result) }
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString()) }
                }
            setLoading(false)
            dismissDeleteDialog()
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
            setLoading(true)
            repository.saveCategory(
                id = state.value.categoryId,
                name = state.value.categoryName,
                userId = state.value.userId
            )
                .onSuccess {
                    loadData()
                }
                .onError { error ->
                    _state.update { it.copy(message = error.asUiText().asString()) }
                }
            dismissModalBottomSheet()
        }
    }

    private fun showModalBottomSheet(id: String, name: String) {
        _state.update {
            it.copy(
                showModalBottomSheet = true,
                categoryId = id,
                categoryName = name
            )
        }
    }

    private fun dismissModalBottomSheet() {
        _state.update { it.copy(showModalBottomSheet = false, categoryName = "") }
    }

    private fun showDeleteDialog(id: String) {
        _state.update { it.copy(showDeleteDialog = true, categoryId = id) }
    }

    private fun dismissDeleteDialog() {
        _state.update { it.copy(showDeleteDialog = false) }
    }

}