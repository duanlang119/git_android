package com.gordongz.github_navigator.ui.page

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gordongz.github_navigator.viewmodel.GithubUserInfoViewModel

@Composable
fun GithubRepoDetailScreen(username: String, repoName: String, navController: NavController, viewModel: GithubUserInfoViewModel = viewModel()) {
    val repoDetail by viewModel.repoDetail.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchRepoDetail(username, repoName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repoDetail?.let { repo ->
            Text(repo.name, style = MaterialTheme.typography.headlineLarge)
            Text(repo.description ?: "No description", style = MaterialTheme.typography.bodyMedium)
            Text("Stars: ${repo.stargazers_count}", style = MaterialTheme.typography.bodySmall)
            Text("Forks: ${repo.forks_count}", style = MaterialTheme.typography.bodySmall)
            Text("Language: ${repo.language}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.html_url))
                context.startActivity(intent)
            }) {
                Text("View on Github")
            }
        }
    }
}
