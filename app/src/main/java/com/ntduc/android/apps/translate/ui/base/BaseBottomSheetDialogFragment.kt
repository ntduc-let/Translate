package com.ntduc.android.apps.translate.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.skydoves.bindables.BindingBottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingBottomSheetDialogFragment<T>(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addEvent()
        addObservers()
        initData()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
                bottomSheet?.setBackgroundResource(android.R.color.transparent)
                this.setCancelable(true)
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) {
            return
        }
        try {
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        if (isAdded) {
            try {
                super.dismissAllowingStateLoss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    open fun initView() {}

    open fun addEvent() {}

    open fun addObservers() {}

    open fun initData() {}
}