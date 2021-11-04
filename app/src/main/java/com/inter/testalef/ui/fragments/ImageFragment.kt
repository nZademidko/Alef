package com.inter.testalef.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.inter.testalef.MainApplication
import com.inter.testalef.R
import com.inter.testalef.databinding.FragmentImageBinding
import com.inter.testalef.databinding.FragmentMainBinding
import javax.inject.Inject

class ImageFragment @Inject constructor() : Fragment(R.layout.fragment_image) {


    private val args by navArgs<ImageFragmentArgs>()
    private val imageUrl by lazy { args.imageUrl }

    private lateinit var binding: FragmentImageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)

        binding.iv.load(imageUrl) {
            error(R.drawable.ic_placeholder)
        }
    }
}