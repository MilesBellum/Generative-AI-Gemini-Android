package com.eagb.generative_ai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eagb.common.UiState
import com.eagb.generative_ai.databinding.FragmentAiBinding
import kotlinx.coroutines.launch

class AiFragment : Fragment() {
    private var _binding: FragmentAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AiViewModel by viewModels()
    private val promptHistory: MutableList<String> = mutableListOf()

    private val aiAdapter = AiAdapter(promptHistory)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAiBinding.inflate(
            inflater,
            container,
            false,
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setUpView()

        viewModel.setUpObservables()
    }

    private fun FragmentAiBinding.setUpView() {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        rvAiHelp.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false,
            )
            adapter = aiAdapter

        }

        edtPrompt.doOnTextChanged { text, _, _, _ ->
            imgSendPrompt.isEnabled = !text.isNullOrEmpty()
            if (!text.isNullOrEmpty()) {
                enableOnEditorActionListener()
            } else {
                disableEditorActionListener()
            }
        }

        imgSendPrompt.setOnClickListener {
            setInputPrompt()
        }
    }

    private fun AiViewModel.setUpObservables() {
        resetUiState()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                uiState.collect { uiState ->
                    onUiStateObserved(uiState)
                }
            }
        }
    }

    private fun enableOnEditorActionListener() {
        binding.edtPrompt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                setInputPrompt()
                true
            } else {
                false
            }
        }
    }

    private fun disableEditorActionListener() {
        binding.edtPrompt.setOnEditorActionListener(null)
    }

    private fun onUiStateObserved(uiState: UiState<String>) {
        with(binding) {
            when (uiState) {
                is UiState.Loading -> {
                    cpiProgress.visibility = View.VISIBLE
                    imgSendPrompt.visibility = View.GONE
                }

                is UiState.Success -> {
                    setOutputPrompt(
                        uiState.outputStream ?: getString(R.string.error_prompt_something_went_wrong)
                    )
                }

                is UiState.Error -> {
                    setOutputPrompt(getString(R.string.error_prompt_something_went_wrong))
                }

                else -> Unit
            }
        }
    }

    private fun setInputPrompt() {
        with(binding) {
            val inputPrompt = edtPrompt.text.toString()
            promptHistory.add(inputPrompt)
            aiAdapter.notifyItemInserted(promptHistory.size - 1)
            edtPrompt.text?.clear()
            rvAiHelp.smoothScrollToPosition(promptHistory.size - 1)
            viewModel.aiStreaming(inputPrompt)
        }
    }

    private fun setOutputPrompt(outputPrompt: CharSequence) {
        with(binding) {
            promptHistory.add(outputPrompt.toString())
            aiAdapter.notifyItemInserted(promptHistory.size - 1)
            rvAiHelp.smoothScrollToPosition(promptHistory.size - 1)

            cpiProgress.visibility = View.INVISIBLE
            imgSendPrompt.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
