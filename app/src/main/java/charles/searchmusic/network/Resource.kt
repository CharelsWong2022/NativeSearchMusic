package charles.searchmusic.network

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val errorCode: String? = null, val errorMessage: String? = null) : Resource<T>()
    class Loading<T>(val isLoading: Boolean) : Resource<T>()
}