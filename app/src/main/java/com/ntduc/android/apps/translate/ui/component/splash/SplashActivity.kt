package com.ntduc.android.apps.translate.ui.component.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.ntduc.android.apps.translate.R
import com.ntduc.android.apps.translate.databinding.ActivitySplashBinding
import com.ntduc.android.apps.translate.ui.base.BaseActivity
import com.ntduc.android.apps.translate.ui.component.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun addEvent() {
        super.addEvent()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, NavigationActivity::class.java))
            finish()
        }, 5000)
    }

    override fun onBackPressed() {}
}
