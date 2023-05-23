package nl.sena.sideproject.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import nl.sena.sideproject.data.remote.api.NumbersService
import nl.sena.sideproject.data.remote.repository.NumbersRepository
import nl.sena.sideproject.data.remote.repository.NumbersRepositoryImpl
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.util.Resource
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NumbersRepositoryImplTest {

    private lateinit var repository: NumbersRepository
    private val service = mock<NumbersService>()

    @Before
    fun setUp() {
        repository = NumbersRepositoryImpl(service)
    }

    @Test
    fun `when service succeeds then returns Success`() = runTest {
        val mockResponse = mock<Numbers>()
        whenever(service.getNumbers()).thenReturn(mockResponse)

        val actual = repository.getNumbers()

        verify(service).getNumbers()
        assertThat(actual).isEqualTo(Resource.Success(mockResponse))
    }

    @Test
    fun `when service fails then returns Error`() = runTest {
        val exception = RuntimeException()
        whenever(service.getNumbers()).doThrow(exception)

        val actual = repository.getNumbers()

        verify(service).getNumbers()
        assertThat(actual).isInstanceOf(Resource.Error::class.java)
    }

}