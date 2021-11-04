package com.inter.testalef.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.inter.testalef.MainApplication
import com.inter.testalef.R
import com.inter.testalef.databinding.FragmentMainBinding
import com.inter.testalef.di.utils.ViewModelFactory
import com.inter.testalef.extensions.launchWhenStarted
import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.models.state.Resource
import com.inter.testalef.ui.adapters.ImageAdapter
import com.inter.testalef.ui.adapters.decorations.ImageVerticalItemDecoration
import com.inter.testalef.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment @Inject constructor() : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MainViewModel

    private val imageAdapter by lazy { ImageAdapter(onItemClicked = onImageClicked) }
    private val imageVerticalItemDecoration by lazy {
        ImageVerticalItemDecoration(16, 32, resources.getInteger(R.integer.images_columns))
    }
    private val onImageClicked = { item: String ->
        val action = MainFragmentDirections.actionMainFragmentToImageFragment(item)
        findNavController().navigate(action)
    }

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        MainApplication.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setupObservers()
        setupUI()
        setupList()
    }

    private fun setupObservers() {

        viewModel.listImagesState.onEach { result ->

            when (result) {

                is Resource.Loading -> onListLoading()

                is Resource.Error -> onListError(result.message)

                is Resource.Success -> onListSuccess(result.data)

            }
        }.launchWhenStarted(lifecycleScope)
    }

    private fun setupList() {
        viewModel.loadImages()
    }

    private fun refreshList() {
        viewModel.refreshImages()
    }

    private fun onListSuccess(data: List<ImageModel>) {
        with(binding) {
            pbContainer.visibility = View.GONE
            errorContainer.visibility = View.GONE
            srlContainer.visibility = View.VISIBLE
        }
        imageAdapter.setData(data)
    }

    private fun onListError(message: String) {
        with(binding) {
            pbContainer.visibility = View.GONE
            errorContainer.visibility = View.VISIBLE
            srlContainer.visibility = View.GONE

            when {
                message.contains("No address associated with hostname") -> {
                    tvError.text = resources.getString(R.string.error_no_internet)
                    ivError.load(R.drawable.ic_baseline_signal_wifi_off_24)
                }
                message.contains("timeout") -> {
                    tvError.text = resources.getString(R.string.error_timeout)
                    ivError.load(R.drawable.ic_baseline_timer_off_24)
                }
                else -> {
                    tvError.text = resources.getString(R.string.error_unknown)
                    ivError.load(R.drawable.ic_baseline_cancel_24)
                }
            }
        }
    }

    private fun onListLoading() {
        with(binding) {
            pbContainer.visibility = View.VISIBLE
            errorContainer.visibility = View.GONE
            srlContainer.visibility = View.GONE
        }
    }

    private fun setupUI() {
        with(binding) {

            with(rvImages) {
                addItemDecoration(imageVerticalItemDecoration)
                adapter = imageAdapter
                layoutManager =
                    GridLayoutManager(
                        requireContext(),
                        resources.getInteger(R.integer.images_columns),
                        GridLayoutManager.VERTICAL,
                        false
                    )
            }

            srlContainer.setOnRefreshListener {
                refreshList()
                srlContainer.isRefreshing = false
            }

            btnTry.setOnClickListener {
                viewModel.loadImages()
            }
        }
    }
}