package com.ntduc.android.apps.translate.ui.component.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ntduc.android.apps.translate.constant.FileType
import com.ntduc.android.apps.translate.data.DataRepositorySource
import com.ntduc.android.apps.translate.data.Resource
import com.ntduc.android.apps.translate.data.dto.file.BaseFile
import com.ntduc.android.apps.translate.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val repository: DataRepositorySource
) : BaseViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val filesLiveDataPrivate = MutableLiveData<Resource<List<BaseFile>>>()
    val filesLiveData: LiveData<Resource<List<BaseFile>>> get() = filesLiveDataPrivate

    fun requestAllFiles() {
        viewModelScope.launch {
            filesLiveDataPrivate.value = Resource.Loading()
            repository.requestAllFiles(listOf(*FileType.ALL)).collect {
                filesLiveDataPrivate.value = it
            }
        }
    }
}