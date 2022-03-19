package com.alicankorkmaz.modernandroiddev.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicankorkmaz.modernandroiddev.arch.SideEffect
import com.alicankorkmaz.modernandroiddev.arch.UiEvent
import com.alicankorkmaz.modernandroiddev.arch.ViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: ViewState, Event: UiEvent, Effect: SideEffect> : ViewModel() {
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    val currentState: State
        get() = viewState.value

    private val _viewState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val uiEvent : MutableSharedFlow<Event> = MutableSharedFlow()

    private val _sideEffect : Channel<Effect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        subscribeToUiEvents()
    }

    private fun subscribeToUiEvents() {
        viewModelScope.launch {
            uiEvent.collect {
                handleUiEvent(it)
            }
        }
    }

    protected abstract fun handleUiEvent(event : Event)

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    fun onUiEvent(event : Event) {
        viewModelScope.launch { uiEvent.emit(event) }
    }

    protected fun sendEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _sideEffect.send(effectValue) }
    }
}