package com.ntduc.baseproject.ui.component.navigation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ntduc.baseproject.R
import com.ntduc.baseproject.constant.FileTypeExtension
import com.ntduc.baseproject.data.dto.file.BaseFile
import com.ntduc.baseproject.databinding.ItemDocumentBinding
import com.ntduc.baseproject.utils.formatBytes
import com.ntduc.baseproject.utils.loadImg
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FileAdapter(
    val context: Context
) : BindingListAdapter<BaseFile, FileAdapter.DocumentViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder =
        parent.binding<ItemDocumentBinding>(R.layout.item_document).let(::DocumentViewHolder)

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class DocumentViewHolder constructor(
        private val binding: ItemDocumentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(baseFile: BaseFile) {
            binding.apply {
                this@apply.baseFile = baseFile
            }

            when (FileTypeExtension.getTypeFile(baseFile.data!!)) {
                FileTypeExtension.AUDIO -> {
                    val coroutineScope =
                        itemView.findViewTreeLifecycleOwner()?.lifecycleScope ?: CoroutineScope(
                            Dispatchers.IO
                        )
                    coroutineScope.launch {
                        val image = try {
                            val mData = MediaMetadataRetriever()
                            mData.setDataSource(baseFile.data)
                            val art = mData.embeddedPicture
                            BitmapFactory.decodeByteArray(art, 0, art!!.size)
                        } catch (e: Exception) {
                            null
                        }

                        withContext(Dispatchers.Main) {
                            context.loadImg(
                                imgUrl = image,
                                view = binding.img,
                                error = FileTypeExtension.getIconFile(baseFile.data!!),
                                placeHolder = R.color.black
                            )
                        }
                    }
                }
                else -> {
                    context.loadImg(
                        imgUrl = baseFile.data!!,
                        view = binding.img,
                        error = FileTypeExtension.getIconFile(baseFile.data!!),
                        placeHolder = R.color.black
                    )
                }
            }
            binding.txtTitle.text = baseFile.displayName
            binding.txtDescription.text = baseFile.size?.formatBytes()

            binding.root.setOnClickListener {
                onClickListener?.let {
                    it(binding.cardView, baseFile)
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BaseFile>() {
            override fun areItemsTheSame(oldItem: BaseFile, newItem: BaseFile): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: BaseFile, newItem: BaseFile): Boolean =
                oldItem == newItem
        }
    }

    private var onClickListener: ((View, BaseFile) -> Unit)? = null

    fun setOnClickListener(listener: (View, BaseFile) -> Unit) {
        onClickListener = listener
    }
}