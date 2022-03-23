package com.alicankorkmaz.modernandroiddev.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alicankorkmaz.modernandroiddev.BR

abstract class BaseDataBindingFragment<B : ViewDataBinding, VM : BaseViewModel<*, *, *>>(@LayoutRes layoutResId: Int) : BaseFragment<VM>(layoutResId) {

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<B>(inflater, layoutResId, container, false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            binding.setVariable(BR.viewModel, viewModel)
        }.root
    }
}
