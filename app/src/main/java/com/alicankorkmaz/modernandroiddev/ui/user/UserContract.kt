package com.alicankorkmaz.modernandroiddev.ui.user

import com.alicankorkmaz.modernandroiddev.arch.SideEffect
import com.alicankorkmaz.modernandroiddev.arch.UiEvent
import com.alicankorkmaz.modernandroiddev.arch.ViewState
import com.alicankorkmaz.modernandroiddev.data.models.remote.GithubUser

class UserContract {

    // Demonstrates usage of ViewState as a data class
    data class State(
        var isLoading: Boolean = false,
        var user: GithubUser? = null
    ) : ViewState

    sealed class Event : UiEvent {
        data class OnFetchButtonClicked(val username: String) : Event()
    }

    sealed class Effect : SideEffect {
        class ShowError(val errorMessage: String) : Effect()
    }

}