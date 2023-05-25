package nl.sena.sideproject.ui.view.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import nl.sena.sideproject.data.remote.response.Numbers
import nl.sena.sideproject.databinding.FragmentOverviewBinding
import nl.sena.sideproject.ui.view.overview.adapter.NumberListAdapter
import nl.sena.sideproject.ui.viewmodel.OverviewUiState
import nl.sena.sideproject.ui.viewmodel.OverviewViewModel
import nl.sena.sideproject.util.removeAnimation
import nl.sena.sideproject.util.showErrorAnimation
import nl.sena.sideproject.util.showLoadingAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModel()
    private var _viewBinding: FragmentOverviewBinding? = null
    private val viewBinding get() = requireNotNull(_viewBinding)
    private val numberListAdapter = NumberListAdapter(::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentOverviewBinding.inflate(LayoutInflater.from(context))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        viewBinding.numberList.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.numberList.adapter = numberListAdapter
    }

    private fun initObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { renderUiStates(it) }
    }

    private fun renderUiStates(state: OverviewUiState) {
        when (state) {
            is OverviewUiState.NumbersLoaded -> showSuccessState(state)
            is OverviewUiState.Error -> showErrorState()
            is OverviewUiState.Loading -> showLoadingState()
        }
    }

    private fun showSuccessState(state: OverviewUiState.NumbersLoaded) {
        numberListAdapter.submitList(state.number.numbers)
        viewBinding.stateAnimation.removeAnimation()
        viewBinding.numberList.visibility = View.VISIBLE
    }

    private fun showErrorState() {
        viewBinding.stateAnimation.showErrorAnimation()
        viewBinding.numberList.visibility = View.INVISIBLE
    }

    private fun showLoadingState() {
        viewBinding.numberList.visibility = View.INVISIBLE
        viewBinding.stateAnimation.showLoadingAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun onClick(number: Numbers.Number) {
        val action = OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(number.value)
        findNavController().navigate(action)
    }

}