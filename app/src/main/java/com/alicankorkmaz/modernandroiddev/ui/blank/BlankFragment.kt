package com.alicankorkmaz.modernandroiddev.ui.blank

import com.alicankorkmaz.modernandroiddev.R
import com.alicankorkmaz.modernandroiddev.arch.SideEffect
import com.alicankorkmaz.modernandroiddev.arch.ViewState
import com.alicankorkmaz.modernandroiddev.common.base.BaseDataBindingFragment
import com.alicankorkmaz.modernandroiddev.databinding.FragmentBlankBinding
import com.alicankorkmaz.modernandroiddev.util.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BlankFragment : BaseDataBindingFragment<FragmentBlankBinding, BlankViewModel>(R.layout.fragment_blank) {

    override fun observeViewState() {
        collectFlow(viewModel.uiState){
            when(it){
                BlankContract.State.Idle -> TODO()
                BlankContract.State.Loading -> TODO()
                is BlankContract.State.Success -> TODO()
            }
        }
    }

    override fun observeSideEffects() {
        collectFlow(viewModel.effect){
            when(it){
                BlankContract.Effect.ShowToast -> TODO()
            }
        }
    }

    companion object {
        fun newInstance() = BlankFragment()
    }
}