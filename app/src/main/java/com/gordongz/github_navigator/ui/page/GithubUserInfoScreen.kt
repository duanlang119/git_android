package com.gordongz.github_navigator.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gordongz.github_navigator.viewmodel.GithubUserInfoViewModel

@Composable
fun GithubUserInfoScreen(viewModel: GithubUserInfoViewModel = viewModel(), navController: NavController) {
    var username by remember { mutableStateOf("") }
    val userInfo by viewModel.userInfo.observeAsState()
    val error by viewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Github User Info", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Github Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchUserInfo(username) }) {
            Text("Fetch User Info")
        }
        Spacer(modifier = Modifier.height(16.dp))
        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        userInfo?.let { user ->
            Text("Username: ${user.login}")
            Text("Name: ${user.name}")
            Text("Public Repos: ${user.public_repos}")
            Text("Followers: ${user.followers}")
            Text("Following: ${user.following}")
            TextButton(onClick = {
                viewModel.fetchUserRepos(user.login)
                navController.navigate("userRepos/${user.login}")
            }) {
                Text("Public Repos")
            }
        }
    }
}
