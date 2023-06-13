package com.ntduc.baseproject.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.doOnPreDraw
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.ntduc.baseproject.R
import com.ntduc.baseproject.utils.ID_NAV_HOST_FRAGMENT
import com.ntduc.baseproject.utils.ID_START_VIEW_MOTION_SCALE
import com.ntduc.baseproject.utils.IS_MOTION_AXIS_Z
import com.ntduc.baseproject.utils.IS_MOTION_ITEM
import com.skydoves.bindables.BindingFragment

abstract class BaseFragment<T : ViewDataBinding> constructor(
    @LayoutRes val contentLayoutId: Int
) : BindingFragment<T>(contentLayoutId) {

    val currentNavigationFragment: Fragment?
        get() = parentFragmentManager.fragments.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isMotionAxisZ = arguments?.getBoolean(IS_MOTION_AXIS_Z, false)
        if (isMotionAxisZ != null && isMotionAxisZ) {
            enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
        }

        val isMotionItem = arguments?.getBoolean(IS_MOTION_ITEM, false)
        val idNavHostFragment = arguments?.getInt(ID_NAV_HOST_FRAGMENT, 0)
        if (isMotionItem != null && isMotionItem && idNavHostFragment != null && idNavHostFragment != 0) {
            sharedElementEnterTransition = MaterialContainerTransform().apply {
                drawingViewId = R.id.nav_host_fragment
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
                scrimColor = Color.TRANSPARENT
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val idStartViewMotionScale = arguments?.getInt(ID_START_VIEW_MOTION_SCALE, 0)
        if (idStartViewMotionScale != null && idStartViewMotionScale != 0) {
            binding.run {
                enterTransition = MaterialContainerTransform().apply {
                    startView = requireActivity().findViewById(idStartViewMotionScale)
                    endView = binding.root
                    duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
                    scrimColor = Color.TRANSPARENT
                }
                returnTransition = Slide().apply {
                    duration = resources.getInteger(R.integer.reply_motion_duration_medium).toLong()
                    addTarget(binding.root.id)
                }
            }
        }

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