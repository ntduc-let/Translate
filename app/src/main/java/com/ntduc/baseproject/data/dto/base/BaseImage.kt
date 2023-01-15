package com.ntduc.baseproject.data.dto.base

import com.ntduc.baseproject.data.dto.base.BaseFile

open class BaseImage(
  id: Long? = null,
  title: String? = null,
  displayName: String? = null,
  mimeType: String? = null,
  size: Long? = null,
  dateAdded: Long? = null,
  dateModified: Long? = null,
  data: String? = null,
  var height: Long? = null,
  var width: Long? = null
) : BaseFile(id, title, displayName, mimeType, size, dateAdded, dateModified, data)