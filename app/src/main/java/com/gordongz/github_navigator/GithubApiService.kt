package com.gordongz.github_navigator

//import com.example.nelson_github_navigator.ui.viewmodel.GithubApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("users/{username}")
    suspend fun getUserInfo(@Path("username") username: String): GithubUser

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<GithubRepo>
}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GithubApiService by lazy {
        retrofit.create(GithubApiService::class.java)
    }
}