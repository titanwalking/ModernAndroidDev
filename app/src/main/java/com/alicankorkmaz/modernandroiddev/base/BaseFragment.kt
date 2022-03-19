package com.alicankorkmaz.modernandroiddev.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM: ViewModel>(@LayoutRes val layoutResId: Int): Fragment(layoutResId) {

    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getVmClass()]
    }

    @Suppress("UNCHECKED_CAST")
    private fun getVmClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }
}