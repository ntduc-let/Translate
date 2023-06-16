package com.ntduc.android.apps.translate.ui.component.navigation.fragment

import androidx.fragment.app.activityViewModels
import com.ntduc.android.apps.translate.R
import com.ntduc.android.apps.translate.databinding.FragmentDetailBinding
import com.ntduc.android.apps.translate.ui.base.BaseFragment
import com.ntduc.android.apps.translate.ui.component.navigation.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel: NavigationViewModel by activityViewModels()
}