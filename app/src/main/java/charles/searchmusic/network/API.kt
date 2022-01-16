package charles.searchmusic.network

import charles.searchmusic.response.GetSearchMusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/search")
    suspend fun getMusic(@Query("term") term: String): Response<GetSearchMusicResponse>
}