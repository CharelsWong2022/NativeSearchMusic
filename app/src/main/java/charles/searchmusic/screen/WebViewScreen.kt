package charles.searchmusic.screen

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import charles.searchmusic.ui.theme.SearchMusicTheme
import charles.searchmusic.viewmodel.WebViewViewModel
import org.koin.androidx.compose.get


@Composable
fun WebViewScreen(
    navController: NavController? = null,
    url: String?,
    viewModel: WebViewViewModel = get()
) {
    val urlState = remember(url) { mutableStateOf(url) }
    var onClick: (() -> Unit)? = null

    Scaffold(
        topBar = {
            Row() {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            onClick?.invoke()
                            viewModel._title.value = ""
                            navController?.popBackStack()
                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(end = 15.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = viewModel.title.value,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    ) {

        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    with(settings) {
                        domStorageEnabled = true
                        javaScriptEnabled = true
                    }
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)

                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            if (!view?.title.isNullOrEmpty()) {
                                viewModel._title.value = view?.title.toString()
                            }

                        }

                    }
                    loadUrl(url ?: "")
                }

            }, update = {
                onClick = {
                    it.onPause()
                    it.destroy()
                }
                it.loadUrl(urlState.value ?: "")
            })

    }

}

@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true)
@Composable
fun DefaultPreview() {
    SearchMusicTheme {
        //   WebViewScreen( url = "a")
    }
}