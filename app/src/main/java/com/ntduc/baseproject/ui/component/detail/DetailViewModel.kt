package com.ntduc.baseproject.ui.component.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ntduc.baseproject.constant.FileType
import com.ntduc.baseproject.data.DataRepositorySource
import com.ntduc.baseproject.data.Resource
import com.ntduc.baseproject.data.dto.files.Files
import com.ntduc.baseproject.ui.base.BaseViewModel
import com.ntduc.baseproject.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DataRepositorySource
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val filesLiveDataPrivate = MutableLiveData<Resource<Files>>()
    val filesLiveData: LiveData<Resource<Files>> get() = filesLiveDataPrivate

    fun requestAllFiles(type: Int){
        viewModelScope.launch {
            filesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                val types = when(type){
                    DetailActivity.ALL_DOCUMENT -> listOf(*FileType.DOCUMENT)
                    DetailActivity.ALL_AUDIO -> listOf(*FileType.AUDIO)
                    DetailActivity.ALL_IMAGE -> listOf(*FileType.IMAGE)
                    DetailActivity.ALL_VIDEO -> listOf(*FileType.VIDEO)
                    else -> listOf(*FileType.ALL)
                }
                repository.requestAllFiles(types).collect{
                    filesLiveDataPrivate.value = it
                }
            }
        }
    }
}