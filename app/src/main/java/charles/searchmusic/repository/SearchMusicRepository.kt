package charles.searchmusic.repository

import charles.searchmusic.network.HandleApiService
import charles.searchmusic.network.RetrofitService

class SearchMusicRepository {
    suspend fun getMusic(term: String) =
        HandleApiService(RetrofitService.getApi.getMusic(term)).getResult()
}