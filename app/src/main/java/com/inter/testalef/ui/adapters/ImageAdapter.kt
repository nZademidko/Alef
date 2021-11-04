package com.inter.testalef.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inter.testalef.R
import com.inter.testalef.databinding.ImageItemsBinding
import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.utils.ImageDiffUtil

class ImageAdapter(private val onItemClicked: (String) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var imagesList = mutableListOf<ImageModel>()

    inner class ImageViewHolder(private val binding: ImageItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageModel: ImageModel) {
            binding.iv.load(imageModel.imageUrl) {
                error(R.drawable.ic_placeholder)
            }
            binding.iv.setOnClickListener {
                onItemClicked(imageModel.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            ImageItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageModel = imagesList[position])
    }

    override fun getItemCount() = imagesList.size

    fun setData(newData: List<ImageModel>) {

        val recipesDiffUtil = ImageDiffUtil(oldList = imagesList, newList = newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        imagesList.clear()
        imagesList.addAll(newData)
        diffUtilResult.dispatchUpdatesTo(this)
    }
}