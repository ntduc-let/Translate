package com.ntduc.baseproject.ui.component.main

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.activity.viewModels
import com.ntduc.baseproject.R
import com.ntduc.baseproject.databinding.ActivityMainBinding
import com.ntduc.baseproject.ui.base.BaseActivity
import com.ntduc.baseproject.ui.component.detail.DetailActivity
import com.ntduc.baseproject.utils.toast.shortToast
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.PermissionBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun addEvent() {
        super.addEvent()

        binding.getAllFile.setOnClickListener {
            startDetail(DetailActivity.ALL_FILE)
        }

        binding.getAllDocument.setOnClickListener {
            startDetail(DetailActivity.ALL_DOCUMENT)
        }

        binding.getAllImage.setOnClickListener {
            startDetail(DetailActivity.ALL_IMAGE)
        }

        binding.getAllVideo.setOnClickListener {
            startDetail(DetailActivity.ALL_VIDEO)

        }

        binding.getAllAudio.setOnClickListener {
            startDetail(DetailActivity.ALL_AUDIO)
        }
    }

    private fun startDetail(type: Int) {
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
                    startActivity(intent)
                } else {
                    shortToast("These permissions are denied: $deniedList")
                }
            }
    }
}