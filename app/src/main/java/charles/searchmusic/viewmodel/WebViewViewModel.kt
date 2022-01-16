package charles.searchmusic.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WebViewViewModel :ViewModel() {
    var _title: MutableState<String> = mutableStateOf("")
    val title: State<String> get() = _title
}