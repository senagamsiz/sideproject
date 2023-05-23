package nl.sena.sideproject.data.remote.repository

import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.util.Resource

interface NumbersRepository {
    suspend fun getNumbers(): Resource<Numbers>
}