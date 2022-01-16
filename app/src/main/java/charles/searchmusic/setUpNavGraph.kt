package charles.searchmusic

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import charles.searchmusic.screen.SearchMusicScreen
import charles.searchmusic.screen.WebViewScreen
import coil.annotation.ExperimentalCoilApi


@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun setUpNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SearchMusicScreen.route) {
        composable(route = Screen.SearchMusicScreen.route) {
            SearchMusicScreen(navController = navController)
        }
        composable(
            route = Screen.WebViewScreen.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) { entry ->
            WebViewScreen(navController = navController, url = entry.arguments?.getString("url"))
        }
    }

}