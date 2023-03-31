package br.com.lucas.todo.presentation

import android.os.Bundle
import android.view.View
import br.com.core.base.viewModel.DummyViewModel
import br.com.core.extensions.startActivityByName
import br.com.lucas.todo.databinding.FragmentSplashScreenBinding
import br.com.core.base.fragment.BaseFragment
import com.gaelmarhic.quadrant.QuadrantConstants

class CustomSplashFragment : BaseFragment<FragmentSplashScreenBinding, DummyViewModel>(
    FragmentSplashScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivCheck.alpha = 0f
        binding.ivCheck.animate().setDuration(1500).alpha(1f).withEndAction {
            activity?.startActivityByName(QuadrantConstants.TASKS_ACTIVITY, true)
        }
    }

}