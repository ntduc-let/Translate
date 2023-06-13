package com.ntduc.baseproject.ui.component.navigation.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntduc.baseproject.R
import com.ntduc.baseproject.data.Resource
import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.databinding.FragmentHomeBinding
import com.ntduc.baseproject.ui.base.BaseFragment
import com.ntduc.baseproject.ui.component.navigation.NavigationViewModel
import com.ntduc.baseproject.ui.component.navigation.adapter.FileAdapter
import com.ntduc.baseproject.utils.navigateToDesWithMotionItem
import com.ntduc.baseproject.utils.observe
import com.ntduc.baseproject.utils.view.gone
import com.ntduc.baseproject.utils.view.visible
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