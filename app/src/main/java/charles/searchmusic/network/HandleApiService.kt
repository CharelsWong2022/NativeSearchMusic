package charles.searchmusic.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

class HandleApiService<T>(private val response: Response<T>) {

    fun getResult(): Flow<Resource<T>> {
        return flow<Resource<T>> {
            try {
                if (response.isSuccessful) {
                    response.body()?.let { resultResponse ->
                        emit(Resource.Success(resultResponse))
                    }
                } else {
                    emit(Resource.Error("no_data", "404"))
                }

            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        emit(Resource.Error("network_error", "500"))
                    }
                    else -> {
                        emit(Resource.Error("conversion_errorr", "500"))
                    }
                }
            } finally {
                emit(Resource.Loading(false))
            }
        }
    }
}