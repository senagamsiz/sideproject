package nl.sena.sideproject.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import nl.sena.sideproject.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _viewBinding: FragmentDetailBinding? = null
    private val viewBinding get() = requireNotNull(_viewBinding)
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateText()
    }

    private fun populateText() {
        viewBinding.detailsText.text = args.numberId
    }
}
