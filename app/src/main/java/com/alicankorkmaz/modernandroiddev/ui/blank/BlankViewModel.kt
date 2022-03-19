package com.alicankorkmaz.modernandroiddev.ui.blank

import androidx.lifecycle.viewModelScope
import com.alicankorkmaz.modernandroiddev.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class BlankViewModel @Inject constructor(

) : BaseViewModel<BlankContract.State, BlankContract.Event, BlankContract.Effect>() {
    override fun createInitialState(): BlankContract.State {
        return BlankContract.State.Idle
    }

    override fun handleUiEvent(event: BlankContract.Event) {
        when (event) {
            BlankContract.Event.OnShowToastClicked -> sendEffect { BlankContract.Effect.ShowToast(toastMessage = "Toast!") }
            BlankContract.Event.SendRequest -> pretendNetworkRequestSent()
        }
    }

    fun pretendNetworkRequestSent() {
        setState { BlankContract.State.Loading }
        viewModelScope.launch {
            delay(2000)
            val successful = Random.nextBoolean()
            if (successful) {
                setState { BlankContract.State.Success(message = "Request Successful") }
            } else {
                setState { BlankContract.State.Error(errorMessage = "Request Failed") }
            }
        }
    }
}