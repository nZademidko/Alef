package com.inter.testalef.utils

import androidx.recyclerview.widget.DiffUtil
import com.inter.testalef.models.entities.model.ImageModel

class ImageDiffUtil(
    private val oldList: List<ImageModel>,
    private val newList: List<ImageModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() =
        oldList.size

    override fun getNewListSize() =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}