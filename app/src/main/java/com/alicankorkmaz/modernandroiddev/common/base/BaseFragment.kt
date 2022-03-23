package com.alicankorkmaz.modernandroiddev.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM : BaseViewModel<*, *, *>>(@LayoutRes val layoutResId: Int) :
    Fragment(layoutResId) {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
        observeSideEffects()
    }

    abstract fun observeViewState()

    abstract fun observeSideEffects()

    abstract fun getViewModelClass(): Class<VM>
}
