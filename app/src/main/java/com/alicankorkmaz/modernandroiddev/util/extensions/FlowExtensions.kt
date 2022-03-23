package com.alicankorkmaz.modernandroiddev.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

fun <T> Flow<T>.observeIn(lifecycleOwner: LifecycleOwner): Job {
    return flowWithLifecycle(lifecycleOwner.lifecycle)
        .launchIn(lifecycleOwner.lifecycle.coroutineScope)
}

fun <T> Flow<T>.observeIn(fragment: Fragment): Job {
    return observeIn(fragment.viewLifecycleOwner)
}