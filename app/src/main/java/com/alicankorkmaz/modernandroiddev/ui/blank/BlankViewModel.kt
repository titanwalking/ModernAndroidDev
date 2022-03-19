package com.alicankorkmaz.modernandroiddev.ui.blank

import com.alicankorkmaz.modernandroiddev.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlankViewModel @Inject constructor(

) : BaseViewModel<BlankContract.State, BlankContract.Event, BlankContract.Effect>() {
    override fun createInitialState(): BlankContract.State {
        return BlankContract.State.Idle
    }

    override fun handleUiEvent(event: BlankContract.Event) {
        when (event) {
            is BlankContract.Event.OnShowToastClicked -> {
                sendEffect { BlankContract.Effect.ShowToast }
            }
        }

    }
}