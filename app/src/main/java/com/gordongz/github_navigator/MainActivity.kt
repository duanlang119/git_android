package com.gordongz.github_navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gordongz.github_navigator.ui.page.GithubRepoDetailScreen
import com.gordongz.github_navigator.ui.page.GithubUserInfoScreen
import com.gordongz.github_navigator.ui.page.GithubUserReposScreen
import com.gordongz.github_navigator.ui.theme.Nelson_github_navigatorTheme
import androidx.navigation.NavType
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nelson_github_navigatorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "userInfo") {
                        composable("userInfo") {
                            GithubUserInfoScreen(navController = navController)
                        }
                        composable(
                            "userRepos/{username}",
                            arguments = listOf(navArgument("username") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username")
                            username?.let {
                                GithubUserReposScreen(username = it, navController = navController)
                            }
                        }
                        composable(
                            "repoDetail/{username}/{repoName}",
                            arguments = listOf(
                                navArgument("username") { type = NavType.StringType },
                                navArgument("repoName") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username")
                            val repoName = backStackEntry.arguments?.getString("repoName")
                            if (username != null && repoName != null) {
                                GithubRepoDetailScreen(username = username, repoName = repoName, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
