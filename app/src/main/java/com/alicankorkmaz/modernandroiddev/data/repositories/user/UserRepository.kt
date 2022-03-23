package com.alicankorkmaz.modernandroiddev.data.repositories.user

import com.alicankorkmaz.modernandroiddev.data.models.remote.GetUserError
import com.alicankorkmaz.modernandroiddev.data.models.remote.GithubUser
import com.alicankorkmaz.modernandroiddev.data.network.GithubService
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val githubService: GithubService
) {
    suspend fun getUser(username: String): NetworkResponse<GithubUser, GetUserError> =
        githubService.getUser(username = username)
}
