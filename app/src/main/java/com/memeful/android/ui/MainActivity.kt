package com.memeful.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.memeful.android.R
import com.memeful.android.databinding.ActivityMainBinding
import com.memeful.android.model.GalleryData
import com.memeful.android.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainAdapter.OnBottomReachedListener {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getGalleryLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.pbLoader.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    it.data?.data?.let { listOfGalleryData ->
                        if (listOfGalleryData.isEmpty().not()) {
                            if (viewModel.pageNo - 1 == 0) {
                                val items = arrayListOf<GalleryData>()
                                items.addAll(listOfGalleryData)
                                binding.rvGallery.adapter = MainAdapter(items, this)
                            } else {
                                (binding.rvGallery.adapter as MainAdapter).updateList(listOfGalleryData)
                            }
                        } else {
                            viewModel.isLastPage = true
                        }
                    } ?: kotlin.run {
                        viewModel.isLastPage = true
                    }
                    binding.pbLoader.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.pbLoader.visibility = View.GONE
                    Log.d(TAG, "ERROR - ${it.message}")
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupUI() {

    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onBottomReached(position: Int) {
        viewModel.loadNextPage()
    }
}