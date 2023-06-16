package com.ntduc.android.apps.translate.ui.component.splash

import com.ntduc.android.apps.translate.data.DataRepositorySource
import com.ntduc.android.apps.translate.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataRepositorySource
) : BaseViewModel()