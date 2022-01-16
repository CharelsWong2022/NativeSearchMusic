package charles.searchmusic

sealed class Screen(val route: String) {
    object SearchMusicScreen : Screen("search_music_screen")
    object WebViewScreen : Screen("web_view_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("?url=$arg")
            }
        }
    }
}