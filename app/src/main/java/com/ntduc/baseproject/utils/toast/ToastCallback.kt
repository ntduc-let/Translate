package com.ntduc.baseproject.utils.toast

import androidx.annotation.StringRes

interface ToastCallback {
  fun showShort(@StringRes string: Int)
  
  fun showShort(string: String)
  
  fun showLong(@StringRes string: Int)
  
  fun showLong(string: String)
}