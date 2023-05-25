package nl.sena.sideproject.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import nl.sena.sideproject.data.remote.repository.NumbersRepository
import nl.sena.sideproject.util.Resource

class OverviewViewModel(
    private val repository: NumbersRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<OverviewUiState>()
    val uiState: LiveData<OverviewUiState>
        get() = _uiState.distinctUntilChanged()

    init {
        dispatch(OverviewUiEvent.LoadNumbers)
    }

    private fun dispatch(event: OverviewUiEvent) {
        when (event) {
            is OverviewUiEvent.LoadNumbers -> loadNumbers()
        }
    }

    private fun loadNumbers() = viewModelScope.launch {
        _uiState.value = OverviewUiState.Loading
        when (val result = repository.getNumbers()) {
            is Resource.Error -> _uiState.value = OverviewUiState.Error(result.exception)
            is Resource.Success -> _uiState.value = OverviewUiState.NumbersLoaded(result.data)
        }
    }
}