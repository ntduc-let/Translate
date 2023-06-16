package com.ntduc.android.apps.translate.constant

import com.ntduc.android.apps.translate.R
import java.io.File

object FileTypeExtension {
    const val IMAGE = "image"
    const val VIDEO = "video"
    const val AUDIO = "audio"
    const val PDF = "pdf"
    const val TXT = "txt"
    const val DOC = "doc"
    const val XLS = "xls"
    const val PPT = "ppt"
    const val COMPRESSED = "compressed"
    const val APK = "apk"
    const val OTHER = "other"

    fun getTypeFile(path: String): String {
        val extension = File(path).extension
        return when {
            FileType.IMAGE.contains(extension) -> IMAGE
            FileType.VIDEO.contains(extension) -> VIDEO
            FileType.AUDIO.contains(extension) -> AUDIO
            FileType.DOCUMENT_PDF.contains(extension) -> PDF
            FileType.DOCUMENT_TXT.contains(extension) -> TXT
            FileType.DOCUMENT_DOC.contains(extension) -> DOC
            FileType.DOCUMENT_XLS.contains(extension) -> XLS
            FileType.DOCUMENT_PPT.contains(extension) -> PPT
            FileType.COMPRESSED.contains(extension) -> COMPRESSED
            FileType.APK.contains(extension) -> APK
            else -> OTHER
        }
    }

    fun getIconFile(path: String): Int {
        val file = File(path)
        if (file.isDirectory) return R.drawable.ic_folder

        val extension = file.extension
        return when {
            FileType.IMAGE.contains(extension) -> R.drawable.ic_image
            FileType.VIDEO.contains(extension) -> R.drawable.ic_video
            FileType.AUDIO.contains(extension) -> R.drawable.ic_music
            FileType.DOCUMENT_PDF.contains(extension) -> R.drawable.ic_pdf
            FileType.DOCUMENT_TXT.contains(extension) -> R.drawable.ic_txt
            FileType.DOCUMENT_DOC.contains(extension) -> R.drawable.ic_doc
            FileType.DOCUMENT_XLS.contains(extension) -> R.drawable.ic_xls
            FileType.DOCUMENT_PPT.contains(extension) -> R.drawable.ic_ppt
            FileType.COMPRESSED.contains(extension) -> R.drawable.zip
            FileType.APK.contains(extension) -> R.drawable.ic_apk
            else -> R.drawable.ic_file
        }
    }
}