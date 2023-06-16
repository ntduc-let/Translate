package com.ntduc.android.apps.translate.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.skydoves.bindables.BindingDialogFragment
import kotlin.math.roundToInt

open class BaseDialogFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int,
    private val widthRatio: Float = 0.9f,
    private val isCanceledOnTouchOutside: Boolean = false,
    private val gravity: Int = Gravity.CENTER
) : BindingDialogFragment<T>(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mDialog = dialog
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
            if (mDialog.window != null) {
                mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mDialog.window!!.setLayout(
                    (requireActivity().resources.displayMetrics.widthPixels * widthRatio).roundToInt(),
                    WindowManager.LayoutParams.WRAP_CONTENT
                )

                val layoutParams = mDialog.window!!.attributes
                layoutParams.gravity = gravity
                mDialog.window!!.attributes = layoutParams
            }
        }

        initView()
        addEvent()
        addObservers()
        initData()
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