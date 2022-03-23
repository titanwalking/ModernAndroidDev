package com.alicankorkmaz.modernandroiddev.ui.user

import androidx.lifecycle.viewModelScope
import com.alicankorkmaz.modernandroiddev.common.base.BaseViewModel
import com.alicankorkmaz.modernandroiddev.data.repositories.user.UserRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<UserContract.State, UserContract.Event, UserContract.Effect>() {
    override fun createInitialState(): UserContract.State = UserContract.State()

    override fun handleUiEvent(event: UserContract.Event) {
        when (event) {
            is UserContract.Event.OnFetchButtonClicked -> fetchUser(event.username)
        }
    }

    private fun fetchUser(username: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true, user = null) }
            when (val networkResponse = userRepository.getUser(username)) {
                is NetworkResponse.Success -> {
                    setState { copy(isLoading = false, user = networkResponse.body) }
                }
                is NetworkResponse.Error -> {
                    setState { copy(isLoading = false, user = null) }
                    val errorMessage = networkResponse.body?.message
                        ?: networkResponse.error?.message ?: "Unknown Error"
                    sendEffect { UserContract.Effect.ShowError(errorMessage = errorMessage) }
                }
            }
        }
    }
}
