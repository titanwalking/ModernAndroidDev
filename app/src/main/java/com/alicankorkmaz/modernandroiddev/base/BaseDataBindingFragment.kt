package com.alicankorkmaz.modernandroiddev.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicankorkmaz.modernandroiddev.BR
import java.lang.reflect.ParameterizedType

abstract class BaseDataBindingFragment<B: ViewDataBinding, VM: ViewModel>(@LayoutRes val layoutResId: Int) : Fragment() {

    private lateinit var binding: B
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getVmClass()]
    }

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

    @Suppress("UNCHECKED_CAST")
    private fun getVmClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }
}