package charles.searchmusic.di

import charles.searchmusic.usecase.GetSearchMusicUseCase
import charles.searchmusic.repository.SearchMusicRepository
import charles.searchmusic.viewmodel.SearchMusicViewModel
import charles.searchmusic.viewmodel.WebViewViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<SearchMusicRepository> { SearchMusicRepository() }
    single<GetSearchMusicUseCase> { GetSearchMusicUseCase(get()) }
    single<WebViewViewModel> { WebViewViewModel() }
    viewModel { SearchMusicViewModel(get()) }
}