package com.ntduc.baseproject.utils

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.ntduc.baseproject.R

fun AppCompatActivity.setupNavigationWithActionBar(
  navController: NavController
): AppBarConfiguration {
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  setupActionBarWithNavController(navController, appBarConfiguration)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithActionBar(
  @IdRes idNavHostFragment: Int
): AppBarConfiguration {
  val navController = findNavController(idNavHostFragment)
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  setupActionBarWithNavController(navController, appBarConfiguration)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithToolBar(
  navController: NavController,
  toolbar: Toolbar
): AppBarConfiguration {
  setSupportActionBar(toolbar)
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  setupActionBarWithNavController(navController, appBarConfiguration)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithToolBar(
  @IdRes idNavHostFragment: Int,
  toolbar: Toolbar
): AppBarConfiguration {
  setSupportActionBar(toolbar)
  val navController = findNavController(idNavHostFragment)
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  setupActionBarWithNavController(navController, appBarConfiguration)
  return appBarConfiguration
}

fun setupNavigationWithCollapsingToolbar(
  navController: NavController,
  collapsingToolbarLayout: CollapsingToolbarLayout,
  toolbar: Toolbar
): AppBarConfiguration {
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithCollapsingToolbar(
  @IdRes idNavHostFragment: Int,
  collapsingToolbarLayout: CollapsingToolbarLayout,
  toolbar: Toolbar
): AppBarConfiguration {
  val navController = findNavController(idNavHostFragment)
  val appBarConfiguration = AppBarConfiguration(navController.graph)
  collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithNavigationBar(
  navController: NavController,
  bottomNavigationView: BottomNavigationView,
  topLevelDestinationIds: Set<Int>,
  isHaveActionBar: Boolean = false
): AppBarConfiguration {
  val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds)
  if (isHaveActionBar) setupActionBarWithNavController(navController, appBarConfiguration)
  bottomNavigationView.setupWithNavController(navController)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithNavigationBar(
  @IdRes idNavHostFragment: Int,
  bottomNavigationView: BottomNavigationView,
  topLevelDestinationIds: Set<Int>,
  isHaveActionBar: Boolean = false
): AppBarConfiguration {
  val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds)
  val navController = findNavController(idNavHostFragment)
  if (isHaveActionBar) setupActionBarWithNavController(navController, appBarConfiguration)
  bottomNavigationView.setupWithNavController(navController)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithNavigationDrawer(
  navController: NavController,
  drawerLayout: DrawerLayout,
  navigationView: NavigationView,
  collapsingToolbarLayout: CollapsingToolbarLayout? = null,
  toolbar: Toolbar? = null,
  bottomNavigationView: BottomNavigationView? = null,
  topLevelDestinationIds: Set<Int>? = null,
  isHaveActionBar: Boolean = false
): AppBarConfiguration {
  if (collapsingToolbarLayout == null && toolbar != null) setSupportActionBar(toolbar)
  val appBarConfiguration =
    if (topLevelDestinationIds == null) AppBarConfiguration(navController.graph, drawerLayout)
    else AppBarConfiguration(topLevelDestinationIds, drawerLayout)
  if (collapsingToolbarLayout == null || toolbar == null) {
    if (isHaveActionBar) setupActionBarWithNavController(navController, appBarConfiguration)
  } else {
    collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
  }
  bottomNavigationView?.setupWithNavController(navController)
  navigationView.setupWithNavController(navController)
  return appBarConfiguration
}

fun AppCompatActivity.setupNavigationWithNavigationDrawer(
  @IdRes idNavHostFragment: Int,
  drawerLayout: DrawerLayout,
  navigationView: NavigationView,
  collapsingToolbarLayout: CollapsingToolbarLayout? = null,
  toolbar: Toolbar? = null,
  bottomNavigationView: BottomNavigationView? = null,
  topLevelDestinationIds: Set<Int>? = null,
  isHaveActionBar: Boolean = false
): AppBarConfiguration {
  if (collapsingToolbarLayout == null && toolbar != null) setSupportActionBar(toolbar)
  val navController = findNavController(idNavHostFragment)
  val appBarConfiguration =
    if (topLevelDestinationIds == null) AppBarConfiguration(navController.graph, drawerLayout)
    else AppBarConfiguration(topLevelDestinationIds, drawerLayout)
  if (collapsingToolbarLayout == null || toolbar == null) {
    if (isHaveActionBar) setupActionBarWithNavController(navController, appBarConfiguration)
  } else {
    collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
  }
  bottomNavigationView?.setupWithNavController(navController)
  navigationView.setupWithNavController(navController)
  return appBarConfiguration
}

fun navigateToDes(
  navController: NavController,
  @IdRes idDes: Int,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  navController.navigate(idDes, args, navOptions, navigatorExtras)
}

fun navigateToDesWithMotionScale(
  navController: NavController,
  @IdRes idDes: Int,
  currentFragment: Fragment,
  startView: View,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  currentFragment.setMotionScaleStart()
  val bundle = (args ?: Bundle()).apply {
    putInt(ID_START_VIEW_MOTION_SCALE, startView.id)
  }
  navController.navigate(idDes, bundle, navOptions, navigatorExtras)
}

fun navigateToDesWithMotionAxisZ(
  navController: NavController,
  @IdRes idDes: Int,
  currentFragment: Fragment,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  currentFragment.setMotionAxisZStart()
  val bundle = (args ?: Bundle()).apply {
    putBoolean(IS_MOTION_AXIS_Z, true)
  }
  navController.navigate(idDes, bundle, navOptions, navigatorExtras)
}

fun Fragment.navigateToDes(
  @IdRes idDes: Int,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  this.removeMotionStart()
  findNavController().navigate(idDes, args, navOptions, navigatorExtras)
}

fun Fragment.navigateToDesWithMotionScale(
  @IdRes idDes: Int,
  startView: View,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  this.setMotionScaleStart()
  val bundle = (args ?: Bundle()).apply {
    putInt(ID_START_VIEW_MOTION_SCALE, startView.id)
  }
  findNavController().navigate(idDes, bundle, navOptions, navigatorExtras)
}

fun Fragment.navigateToDesWithMotionItem(
  @IdRes idDes: Int,
  startView: View,
  endTransitionName: String,
  @IdRes idNavHostFragment: Int,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION
) {
  this.setMotionScaleStart()
  val extras = FragmentNavigatorExtras(startView to endTransitionName)
  val bundle = (args ?: Bundle()).apply {
    putBoolean(IS_MOTION_ITEM, true)
    putInt(ID_NAV_HOST_FRAGMENT, idNavHostFragment)
  }
  findNavController().navigate(idDes, bundle, navOptions, extras)
}

fun Fragment.navigateToDesWithMotionAxisZ(
  @IdRes idDes: Int,
  args: Bundle? = null,
  navOptions: NavOptions? = DEFAULT_NAV_OPTION,
  navigatorExtras: Navigator.Extras? = null
) {
  this.setMotionAxisZStart()
  val bundle = (args ?: Bundle()).apply {
    putBoolean(IS_MOTION_AXIS_Z, true)
  }
  findNavController().navigate(idDes, bundle, navOptions, navigatorExtras)
}

fun Fragment.removeMotionStart() =
  this.apply {
    exitTransition = null
    reenterTransition = null
  }

fun Fragment.setMotionScaleStart() =
  this.apply {
    exitTransition = MaterialElevationScale(false).apply {
      duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
    }
    reenterTransition = MaterialElevationScale(true).apply {
      duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
    }
  }

fun Fragment.setMotionAxisZStart() =
  this.apply {
    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
      duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
    }
    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
      duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
    }
  }

private val DEFAULT_NAV_OPTION = navOptions {
  anim {
    enter = R.anim.slide_in_right
    exit = R.anim.slide_out_left
    popEnter = R.anim.slide_in_left
    popExit = R.anim.slide_out_right
  }
}

const val ID_START_VIEW_MOTION_SCALE = "ID_START_VIEW_MOTION_SCALE"
const val IS_MOTION_AXIS_Z = "IS_MOTION_AXIS_Z"
const val IS_MOTION_ITEM = "IS_MOTION_ITEM"
const val ID_NAV_HOST_FRAGMENT = "ID_NAV_HOST_FRAGMENT"