package com.alicankorkmaz.modernandroiddev.ui.blank

import com.alicankorkmaz.modernandroiddev.arch.SideEffect
import com.alicankorkmaz.modernandroiddev.arch.UiEvent
import com.alicankorkmaz.modernandroiddev.arch.ViewState

class BlankContract {

    sealed class State : ViewState {
        object Idle : State()
        object Loading : State()
        data class Success(val message: String) : State()
        data class Error(val errorMessage: String) : State()
    }

    sealed class Event : UiEvent {
        object SendRequest : Event()
        object OnShowToastClicked : Event()
    }

    sealed class Effect : SideEffect {
        data class ShowToast(val toastMessage: String) : Effect()
    }
}