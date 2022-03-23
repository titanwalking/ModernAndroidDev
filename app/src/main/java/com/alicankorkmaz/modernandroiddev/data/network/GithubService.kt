package com.alicankorkmaz.modernandroiddev.data.network

import com.alicankorkmaz.modernandroiddev.data.models.remote.GetUserError
import com.alicankorkmaz.modernandroiddev.data.models.remote.GithubUser
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): NetworkResponse<GithubUser, GetUserError>
}
