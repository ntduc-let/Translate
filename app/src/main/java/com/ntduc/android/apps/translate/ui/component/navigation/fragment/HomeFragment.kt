package com.ntduc.android.apps.translate.ui.component.navigation.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntduc.android.apps.translate.R
import com.ntduc.android.apps.translate.data.Resource
import com.ntduc.android.apps.translate.data.dto.file.BaseFile
import com.ntduc.android.apps.translate.databinding.FragmentHomeBinding
import com.ntduc.android.apps.translate.ui.base.BaseFragment
import com.ntduc.android.apps.translate.ui.component.navigation.NavigationViewModel
import com.ntduc.android.apps.translate.ui.component.navigation.adapter.FileAdapter
import com.ntduc.android.apps.translate.utils.navigateToDesWithMotionItem
import com.ntduc.android.apps.translate.utils.observe
import com.ntduc.android.apps.translate.utils.view.gone
import com.ntduc.android.apps.translate.utils.view.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: NavigationViewModel by activityViewModels()
    private lateinit var fileAdapter: FileAdapter

    override fun initView() {
        super.initView()

        fileAdapter = FileAdapter(requireContext())
        binding.rcv.apply {
            adapter = fileAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun addEvent() {
        super.addEvent()

        fileAdapter.setOnClickListener { view, item ->
            navigateToDesWithMotionItem(R.id.detailFragment, view, getString(R.string.transition_name_detail), R.id.nav_host_fragment)
        }
    }

    override fun addObservers() {
        super.addObservers()

        observe(viewModel.filesLiveData, ::handleListFile)
    }

    private fun handleListFile(status: Resource<List<BaseFile>>) {
        when (status) {
            is Resource.Loading -> {
                showLoadingView()
            }

            is Resource.Success -> status.data?.let {
                bindListFile(list = it)
            }

            is Resource.DataError -> {
                status.errorCode?.let { Log.d("ntduc_debug", "handleListFile: Error " + it) }
            }
        }
    }

    private fun bindListFile(list: List<BaseFile>) {
        binding.loading.gone()
        if (list.isEmpty()) {
            binding.empty.visible()
            binding.rcv.gone()
        } else {
            binding.empty.gone()
            binding.rcv.visible()
        }
        fileAdapter.submitList(list)
    }

    private fun showLoadingView() {
        binding.loading.visible()
    }
}