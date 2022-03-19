package com.alicankorkmaz.modernandroiddev.ui.blank

import com.alicankorkmaz.modernandroiddev.arch.SideEffect
import com.alicankorkmaz.modernandroiddev.arch.UiEvent
import com.alicankorkmaz.modernandroiddev.arch.ViewState

class BlankContract {

    sealed class State : ViewState {
        object Idle : State()
        object Loading : State()
        data class Success(val message: String) : State()
    }

    sealed class Event : UiEvent {
        object OnShowToastClicked : Event()
    }

    sealed class Effect : SideEffect {
        object ShowToast : Effect()
    }
}