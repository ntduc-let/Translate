package com.ntduc.baseproject.ui.base

import com.ntduc.baseproject.usecase.errors.ErrorManager
import com.skydoves.bindables.BindingViewModel
import javax.inject.Inject


/**
 * Created by TruyenIT
 */


abstract class BaseViewModel : BindingViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
