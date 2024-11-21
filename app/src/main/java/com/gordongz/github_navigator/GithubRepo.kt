package com.gordongz.github_navigator

data class GithubRepo(
    val name: String,
    val description: String?,
    val stargazers_count: Int,
    val forks_count: Int,
    val language: String,
    val url: String,
    val html_url: String
)