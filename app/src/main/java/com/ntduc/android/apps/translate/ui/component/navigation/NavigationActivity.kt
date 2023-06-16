package com.ntduc.android.apps.translate.ui.component.navigation

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.ntduc.android.apps.translate.R
import com.ntduc.android.apps.translate.databinding.ActivityNavigationBinding
import com.ntduc.android.apps.translate.ui.base.BaseActivity
import com.ntduc.android.apps.translate.utils.removeMotionStart
import com.ntduc.android.apps.translate.utils.setupNavigationWithNavigationDrawer
import com.ntduc.android.apps.translate.utils.toast.shortToast
import com.ntduc.android.apps.translate.utils.view.gone
import com.ntduc.android.apps.translate.utils.view.visible
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.PermissionBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : BaseActivity<ActivityNavigationBinding>(R.layout.activity_navigation) {

    private val viewModel: NavigationViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun initView() {
        super.initView()

        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = setupNavigationWithNavigationDrawer(
            navController = navController,
            drawerLayout = binding.drawer,
            navigationView = binding.navView,
            bottomNavigationView = binding.bnv,
            topLevelDestinationIds = setOf(R.id.homeFragment, R.id.settingFragment)
        )
    }

    override fun addEvent() {
        super.addEvent()

        navController.addOnDestinationChangedListener { _: NavController?, navDestination: NavDestination, _: Bundle? ->
            when (navDestination.id) {
                R.id.homeFragment, R.id.settingFragment -> binding.bnv.visible()
                else -> binding.bnv.gone()
            }
        }

        binding.bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    currentNavigationFragment?.removeMotionStart()
                    navController.popBackStack(R.id.homeFragment, false)
                }

                R.id.settingFragment -> {
                    currentNavigationFragment?.removeMotionStart()
                    navController.popBackStack(R.id.settingFragment, false)
                }
            }
            NavigationUI.onNavDestinationSelected(it, navController)
            return@setOnItemSelectedListener true
        }
    }

    override fun initData() {
        super.initData()

        requestPermission()
    }

    private fun requestPermission() {
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
                    viewModel.requestAllFiles()
                } else {
                    shortToast("These permissions are denied: $deniedList")
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    fun openMenu() {
        binding.drawer.openDrawer(GravityCompat.START)
    }
}