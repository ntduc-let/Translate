package com.ntduc.baseproject.ui.component.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import com.ntduc.baseproject.R
import com.ntduc.baseproject.databinding.ActivityMainBinding
import com.ntduc.baseproject.ui.base.BaseActivity
import com.ntduc.baseproject.ui.component.detail.DetailActivity
import com.ntduc.baseproject.utils.toast.shortToast
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.PermissionBuilder
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
    }

    override fun addEvent() {
        super.addEvent()

        binding.getAllFile.setOnClickListener {
            startDetail(binding.transFile, DetailActivity.ALL_FILE)
        }

        binding.getAllDocument.setOnClickListener {
            startDetail(binding.transDocument, DetailActivity.ALL_DOCUMENT)
        }

        binding.getAllImage.setOnClickListener {
            startDetail(binding.transImage, DetailActivity.ALL_IMAGE)
        }

        binding.getAllVideo.setOnClickListener {
            startDetail(binding.transVideo, DetailActivity.ALL_VIDEO)

        }

        binding.getAllAudio.setOnClickListener {
            startDetail(binding.transAudio, DetailActivity.ALL_AUDIO)
        }
    }

    private fun startDetail(transformationLayout: TransformationLayout, type: Int) {
        //https://developer.android.com/about/versions/13/behavior-changes-13
        val permission: PermissionBuilder =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                PermissionX.init(this)
                    .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
            } else {
                PermissionX.init(this)
                    .permissions(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_VIDEO,
                        Manifest.permission.READ_MEDIA_AUDIO
                    )
            }
        permission.onExplainRequestReason { scope, deniedList ->
            scope.showRequestReasonDialog(
                deniedList,
                "Core fundamental are based on these permissions",
                "OK",
                "Cancel"
            )
        }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow necessary permissions in Settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TYPE_FILE, type)
                    TransformationCompat.startActivity(transformationLayout, intent)
                } else {
                    shortToast("These permissions are denied: $deniedList")
                }
            }
    }
}