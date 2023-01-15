package com.ntduc.baseproject.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.ntduc.baseproject.ui.base.BaseActivity
import com.ntduc.baseproject.databinding.SplashLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by TruyenIT
 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun observeViewModel() {
    }


}
