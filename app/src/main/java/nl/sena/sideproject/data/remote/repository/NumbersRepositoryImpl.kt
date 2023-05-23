package nl.sena.sideproject.data.remote.repository

import nl.sena.sideproject.data.remote.api.NumbersService
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.util.Resource
import nl.sena.sideproject.util.safeApiCall

class NumbersRepositoryImpl(
    private val service: NumbersService
) : NumbersRepository {
    override suspend fun getNumbers(): Resource<Numbers> {
        return safeApiCall { service.getNumbers() }
    }
}