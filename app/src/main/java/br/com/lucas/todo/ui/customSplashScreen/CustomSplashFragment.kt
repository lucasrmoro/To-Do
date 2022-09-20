package br.com.lucas.todo.ui.customSplashScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.extensions.hideAppBar
import br.com.lucas.todo.databinding.FragmentSplashScreenBinding


class CustomSplashFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideAppBar()
        binding.ivCheck.alpha = 0f
        binding.ivCheck.animate().setDuration(3000).alpha(1f).withEndAction {
            view.findNavController().navigate(R.id.fromSplashToListTask)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}