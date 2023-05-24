package nl.sena.sideproject.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nl.sena.sideproject.MainDispatcherRule
import nl.sena.sideproject.data.remote.repository.NumbersRepository
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.util.Resource
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class OverviewViewModelTest {

    private lateinit var viewModel: OverviewViewModel
    private val repository = mock<NumbersRepository>()

    private val uiStateObserver = mock<Observer<OverviewUiState>>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when repository succeeds for numbers then returns Loading and NumbersLoaded ui states`() =
        runTest {
            val mockNumbers = mock<Numbers>()
            whenever(repository.getNumbers()).thenReturn(Resource.Success(mockNumbers))
            viewModel = createViewModel()
            advanceUntilIdle()
            verify(uiStateObserver).onChanged(OverviewUiState.Loading)
            verify(uiStateObserver).onChanged(OverviewUiState.NumbersLoaded(mockNumbers))
        }

    @Test
    fun `when repository fails for numbers then returns Loading and Error ui states`() =
        runTest {
            val exception = IllegalArgumentException()
            whenever(repository.getNumbers()).thenReturn(Resource.Error(exception))
            viewModel = createViewModel()
            advanceUntilIdle()
            verify(uiStateObserver).onChanged(OverviewUiState.Loading)
            verify(uiStateObserver).onChanged(OverviewUiState.Error(exception))
        }

    private fun createViewModel() = OverviewViewModel(repository).apply {
        this.uiState.observeForever(uiStateObserver)
    }

}