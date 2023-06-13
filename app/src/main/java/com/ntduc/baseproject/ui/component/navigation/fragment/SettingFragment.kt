package com.ntduc.baseproject.ui.component.navigation.fragment

import androidx.fragment.app.activityViewModels
import com.ntduc.baseproject.R
import com.ntduc.baseproject.databinding.FragmentSettingBinding
import com.ntduc.baseproject.ui.base.BaseFragment
import com.ntduc.baseproject.ui.component.navigation.NavigationActivity
import com.ntduc.baseproject.ui.component.navigation.NavigationViewModel
import com.ntduc.baseproject.utils.navigateToDesWithMotionAxisZ
import com.ntduc.baseproject.utils.navigateToDesWithMotionScale
import com.ntduc.baseproject.utils.view.transformMotionView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel: NavigationViewModel by activityViewModels()

    override fun addEvent() {
        super.addEvent()

        binding.openMenu.setOnClickListener {
            (requireActivity() as NavigationActivity).openMenu()
        }

        binding.goToDetail.setOnClickListener {
            navigateToDesWithMotionScale(R.id.detailFragment, it)
        }

        binding.goToHome.setOnClickListener {
            navigateToDesWithMotionAxisZ(R.id.homeFragment)
        }

        binding.viewAnimation1.setOnClickListener {
            it.transformMotionView(binding.viewAnimation2, binding.linear)
        }

        binding.viewAnimation2.setOnClickListener {
            it.transformMotionView(binding.viewAnimation1, binding.linear)
        }
    }
}