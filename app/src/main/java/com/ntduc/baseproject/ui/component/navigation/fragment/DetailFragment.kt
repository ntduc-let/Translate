package com.ntduc.baseproject.ui.component.navigation.fragment

import androidx.fragment.app.activityViewModels
import com.ntduc.baseproject.R
import com.ntduc.baseproject.databinding.FragmentDetailBinding
import com.ntduc.baseproject.ui.base.BaseFragment
import com.ntduc.baseproject.ui.component.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: NavigationViewModel by activityViewModels()
}