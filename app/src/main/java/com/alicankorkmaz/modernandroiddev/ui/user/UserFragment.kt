package com.alicankorkmaz.modernandroiddev.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.alicankorkmaz.modernandroiddev.R
import com.alicankorkmaz.modernandroiddev.common.base.BaseDataBindingFragment
import com.alicankorkmaz.modernandroiddev.databinding.FragmentUserBinding
import com.alicankorkmaz.modernandroiddev.util.extensions.collectFlow
import com.alicankorkmaz.modernandroiddev.util.extensions.hideKeyboard
import com.alicankorkmaz.modernandroiddev.util.extensions.isNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment :
    BaseDataBindingFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtUsername.doOnTextChanged { text, _, _, _ ->
            binding.btnFetch.isEnabled = !text.isNullOrBlank()
        }

        binding.btnFetch.setOnClickListener {
            it.hideKeyboard()
            viewModel.onUiEvent(UserContract.Event.OnFetchButtonClicked(binding.edtUsername.text.toString()))
        }
    }

    override fun observeViewState() {
        collectFlow(viewModel.viewState) {
            renderViewState(it)
        }
    }

    private fun renderViewState(state: UserContract.State) {
        binding.progressBar.visibility = if (state.isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.textView.text = if (state.user.isNull()) {
            ""
        } else {
            state.user.toString()
        }
    }

    override fun observeSideEffects() {
        collectFlow(viewModel.sideEffect) {
            when (it) {
                is UserContract.Effect.ShowError -> Toast.makeText(
                    requireContext(),
                    it.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getViewModelClass(): Class<UserViewModel> = UserViewModel::class.java

    companion object {
        fun newInstance() = UserFragment()
    }
}
