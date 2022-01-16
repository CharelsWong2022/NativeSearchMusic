package charles.searchmusic.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import charles.searchmusic.Memory
import charles.searchmusic.R
import charles.searchmusic.Screen
import charles.searchmusic.request.GetSearchMusicRequest
import charles.searchmusic.response.GetSearchMusicResponse
import charles.searchmusic.viewmodel.SearchMusicViewModel
import charles.searchmusic.weight.CircularProgress
import charles.searchmusic.weight.SearchTextField
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.get


@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SearchMusicScreen(viewModel: SearchMusicViewModel = get(), navController: NavController) {
    val getMovieResponse = viewModel.getMusicSharedFlow.collectAsState(initial = null)
    val isLoading = viewModel.isLoading.collectAsState(false)
    val listState = rememberLazyListState()

    Scaffold(
        content = {

            Column(modifier = Modifier.background(color = Color.LightGray)) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .height(5.dp)
                )
                SearchTextField(
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            null,
                            tint = LocalContentColor.current.copy(alpha = 0.3f)
                        )
                    },
                    trailingIcon = null,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .background(
                            MaterialTheme.colors.surface,
                            RoundedCornerShape(percent = 50)
                        )
                        .padding(4.dp)
                        .height(30.dp),
                    fontSize = 10.sp,
                    placeholderText = "",
                    onClick = {
                        Memory.searchText = it
                        viewModel.fetchMusicList(GetSearchMusicRequest(it))
                    }

                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .height(5.dp)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .height(1.dp)
                )
                getMovieResponse.value?.let { musicList ->
                    musicList.let {
                        LazyColumn(state = listState) {
                            itemsIndexed(items = it) { index, item ->
                                val value = item.collectionViewUrl.toString()
                                MusicItem(album = item, onClick = {
                                    navController.navigate(Screen.WebViewScreen.withArgs("${value}"))
                                })
                            }

                        }
                    }
                }
            }

            getMovieResponse.value?.let {
                viewModel._isLoading.value = !it.isNotEmpty()
            }

            if (isLoading.value) {
                CircularProgress()
            }
        }
    )

}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun MusicItem(album: GetSearchMusicResponse.Album, onClick: (() -> Unit)) {
    Card(shape = RoundedCornerShape(0.dp), elevation = 0.dp, onClick = {
        onClick.invoke()
    }) {
        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                val painter =
                    rememberImagePainter(data = album.artworkUrl100,
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_foreground)
                            error(R.drawable.error_image)
                        })
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = 15.dp, top = 5.dp, bottom = 5.dp)
                        .width(50.dp)
                        .height(50.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(top = 5.dp, bottom = 5.dp)
                ) {
                    Text(
                        maxLines = 1,
                        text = album.trackName ?: "",
                        modifier = Modifier,
                        fontSize = 15.sp
                    )
                    Text(
                        text = album.artistName ?: "",
                        modifier = Modifier,
                        color = colorResource(id = R.color.a6a6a6),
                        fontSize = 10.sp
                    )
                }

            }
        }
    }

}