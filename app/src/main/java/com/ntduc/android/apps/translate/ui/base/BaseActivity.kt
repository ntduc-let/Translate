package com.ntduc.android.apps.translate.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.skydoves.bindables.BindingActivity

abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingActivity<T>(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
