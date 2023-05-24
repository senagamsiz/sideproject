package nl.sena.sideproject.ui.viewmodel

import nl.sena.sideproject.data.remote.response.Numbers

sealed class OverviewUiState {
    object Loading : OverviewUiState()
    data class Error(val exception: Exception) : OverviewUiState()
    data class NumbersLoaded(val number: Numbers) : OverviewUiState()
}
