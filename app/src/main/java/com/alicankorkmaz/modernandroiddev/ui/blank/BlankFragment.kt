package com.alicankorkmaz.modernandroiddev.ui.blank

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alicankorkmaz.modernandroiddev.R
import com.alicankorkmaz.modernandroiddev.common.base.BaseDataBindingFragment
import com.alicankorkmaz.modernandroiddev.databinding.FragmentBlankBinding
import com.alicankorkmaz.modernandroiddev.util.extensions.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment :
    BaseDataBindingFragment<FragmentBlankBinding, BlankViewModel>(R.layout.fragment_blank) {

    override fun getViewModelClass(): Class<BlankViewModel> = BlankViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRequest.setOnClickListener {
            viewModel.onUiEvent(BlankContract.Event.SendRequest)
        }
        binding.btnShowToast.setOnClickListener {
            viewModel.onUiEvent(BlankContract.Event.OnShowToastClicked)
        }
    }

    override fun observeViewState() {
        collectFlow(viewModel.viewState) {
            when (it) {
                BlankContract.State.Idle -> binding.txtCurrentState.text = "Ready"
                BlankContract.State.Loading -> binding.txtCurrentState.text = "Request in Progress"
                is BlankContract.State.Success -> binding.txtCurrentState.text = it.message
                is BlankContract.State.Error -> binding.txtCurrentState.text = it.errorMessage
            }
        }
    }

    override fun observeSideEffects() {
        collectFlow(viewModel.sideEffect) {
            when (it) {
                is BlankContract.Effect.ShowToast -> {
                    Toast.makeText(requireContext(), it.toastMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun newInstance() = BlankFragment()
    }
}
