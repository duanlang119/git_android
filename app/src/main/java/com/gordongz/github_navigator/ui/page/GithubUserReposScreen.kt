package com.gordongz.github_navigator.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gordongz.github_navigator.GithubRepo
import com.gordongz.github_navigator.viewmodel.GithubUserInfoViewModel

@Composable
fun GithubUserReposScreen(username: String, navController: NavController, viewModel: GithubUserInfoViewModel = viewModel()) {
    val userRepos by viewModel.userRepos.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(username) {
        viewModel.fetchUserRepos(username)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        LazyColumn {
            items(userRepos) { repo ->
                RepoItem(repo = repo, username = username, navController = navController)
            }
        }
    }
}

@Composable
fun RepoItem(repo: GithubRepo, username: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("repoDetail/${username}/${repo.name}")
            }
    ) {
        Text(repo.name, style = MaterialTheme.typography.headlineSmall)
        Text(repo.description ?: "No description", style = MaterialTheme.typography.bodyMedium)
        Text("Stars: ${repo.stargazers_count}", style = MaterialTheme.typography.bodySmall)
    }
}
