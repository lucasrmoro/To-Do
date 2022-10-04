package br.com.lucas.todo.presentation.customSplashScreen

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import br.com.lucas.todo.R
import br.com.lucas.todo.core.ext.getMainActivity
import br.com.lucas.todo.databinding.FragmentSplashScreenBinding
import br.com.lucas.todo.presentation.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomSplashFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMainActivity()?.hideToolbar()
        binding.ivCheck.alpha = 0f
        binding.ivCheck.animate().setDuration(1500).alpha(1f).withEndAction {
            getMainActivity()?.showToolbar()
            view.findNavController().navigate(R.id.fromSplashToListTask)
        }
    }

}