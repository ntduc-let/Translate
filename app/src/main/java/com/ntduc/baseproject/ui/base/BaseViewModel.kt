package com.ntduc.baseproject.ui.base

import com.ntduc.baseproject.data.error.ErrorManager
import com.skydoves.bindables.BindingViewModel
import javax.inject.Inject


abstract class BaseViewModel : BindingViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
