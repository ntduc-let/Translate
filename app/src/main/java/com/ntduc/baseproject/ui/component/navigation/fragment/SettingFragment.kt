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

        binding.visibleAnimation.setOnClickListener {
            it.transformMotionView(binding.viewAnimation, binding.linear)
        }

        binding.viewAnimation.setOnClickListener {
            it.transformMotionView(binding.visibleAnimation, binding.linear)
        }
    }
}