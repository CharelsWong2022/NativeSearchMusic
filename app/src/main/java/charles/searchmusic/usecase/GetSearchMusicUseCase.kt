package charles.searchmusic.usecase

import charles.searchmusic.network.Resource
import charles.searchmusic.request.GetSearchMusicRequest
import charles.searchmusic.response.GetSearchMusicResponse
import charles.searchmusic.repository.SearchMusicRepository
import kotlinx.coroutines.flow.Flow

class GetSearchMusicUseCase(private val searchRepo: SearchMusicRepository) {
    suspend operator fun invoke(request: GetSearchMusicRequest): Flow<Resource<GetSearchMusicResponse>> {
        return searchRepo.getMusic(request.name)
    }


}