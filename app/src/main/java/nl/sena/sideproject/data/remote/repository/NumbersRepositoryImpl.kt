package nl.sena.sideproject.data.remote.repository

import kotlinx.coroutines.delay
import nl.sena.sideproject.data.remote.api.NumbersService
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.util.Resource
import nl.sena.sideproject.util.safeApiCall

class NumbersRepositoryImpl(
    private val service: NumbersService
) : NumbersRepository {
    override suspend fun getNumbers(): Resource<Numbers> {
        delay(3000)
        return safeApiCall { service.getNumbers() }
    }
}