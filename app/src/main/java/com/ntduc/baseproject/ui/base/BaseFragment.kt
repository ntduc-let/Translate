package com.ntduc.baseproject.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.skydoves.bindables.BindingFragment

abstract class BaseFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addEvent()
        addObservers()
        initData()
    }

    open fun initView() {}

    open fun addEvent() {}

    open fun addObservers() {}

    open fun initData() {}
}