package com.ntduc.baseproject.ui.component.detail

import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ntduc.baseproject.R
import com.ntduc.baseproject.data.Resource
import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.databinding.ActivityDetailBinding
import com.ntduc.baseproject.ui.adapter.DetailAdapter
import com.ntduc.baseproject.ui.base.BaseActivity
import com.ntduc.baseproject.utils.observe
import com.ntduc.baseproject.utils.view.gone
import com.ntduc.baseproject.utils.view.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    companion object {
        const val TYPE_FILE = "TYPE_FILE"

        const val ALL_FILE = 0
        const val ALL_DOCUMENT = 100
        const val ALL_AUDIO = 200
        const val ALL_IMAGE = 300
        const val ALL_VIDEO = 400
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var detailAdapter: DetailAdapter
    private var type: Int = ALL_FILE

    override fun onResume() {
        super.onResume()
        viewModel.requestAllFiles(type)
    }

    override fun initView() {
        super.initView()

        detailAdapter = DetailAdapter(this)
        binding.rcv.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun addObservers() {
        super.addObservers()
        observe(viewModel.filesLiveData, ::handleListFile)
    }

    override fun initData() {
        super.initData()

        type = intent.getIntExtra(TYPE_FILE, ALL_FILE)
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
                status.errorCode?.let { Log.d("ntduc_debug", "handleHighlightsList: Error " + it) }
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
        detailAdapter.submitList(list)
    }

    private fun showLoadingView() {
        binding.loading.visible()
    }
}