package charles.searchmusic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import charles.searchmusic.Memory
import charles.searchmusic.network.Resource
import charles.searchmusic.request.GetSearchMusicRequest
import charles.searchmusic.response.GetSearchMusicResponse
import charles.searchmusic.usecase.GetSearchMusicUseCase
import charles.searchmusic.util.Utils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchMusicViewModel(
    private val getSearchMusicUseCase: GetSearchMusicUseCase
) : ViewModel() {
    private val _getSearchMusicSharedFlow = MutableSharedFlow<GetSearchMusicResponse>()
    val getMusicSharedFlow = _getSearchMusicSharedFlow.map { response ->
        response.results.filter {
            !it.artistName.isNullOrEmpty() && !it.trackName.isNullOrEmpty() &&
                    !it.trackViewUrl.isNullOrEmpty() && it.kind.equals("song")
        }
    }

    val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        if (Memory.searchText.isNotEmpty()) {
            fetchMusicList(GetSearchMusicRequest(Memory.searchText))
        }
    }

    fun fetchMusicList(request: GetSearchMusicRequest) {
        viewModelScope.launch {
            getSearchMusicUseCase(request)
                .onStart { _isLoading.emit(true) }
                .collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { data ->
                                _getSearchMusicSharedFlow.emit(data)
                            }
                        }
                        is Resource.Error -> {
                        }
                        is Resource.Loading -> {
                        }
                    }
                }
        }
    }
}