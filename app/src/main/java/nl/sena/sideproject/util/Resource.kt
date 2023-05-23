package nl.sena.sideproject.util

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
}

fun <T : Any, R : Any> Resource<T>.map(mapper: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Error -> this
        is Resource.Success -> try {
            Resource.Success(mapper.invoke(this.data))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
