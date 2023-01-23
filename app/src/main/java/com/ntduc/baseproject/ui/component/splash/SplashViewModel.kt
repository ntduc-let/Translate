package com.ntduc.baseproject.ui.component.splash

import com.ntduc.baseproject.data.DataRepositorySource
import com.ntduc.baseproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataRepositorySource
) : BaseViewModel()