package com.memeful.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.memeful.android.R
import com.memeful.android.databinding.ActivityMainBinding
import com.memeful.android.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
                    Log.d(TAG, "LOADING")
                    Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "SUCCESS")
                    Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
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
}