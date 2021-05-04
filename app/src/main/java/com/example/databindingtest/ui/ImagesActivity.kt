package com.example.databindingtest.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingtest.R
import com.example.databindingtest.adapter.RecAdapter
import com.example.databindingtest.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity() {

    lateinit var binding: ActivityImagesBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var recAdapter: RecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        setupRecycler()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.photos.observe(this, Observer {
            it.body()?.hits?.let { hits ->
                recAdapter.setList(hits)
            }
        })

        mainViewModel.getSingleRecyclerEvent().observe(this, Observer { url ->
            Log.d("myLog", url)
            supportFragmentManager.apply {
                popBackStack()
                beginTransaction()
                    .replace(R.id.constraintLayoutImages, ImageFragment.newInstance(url))
                    .addToBackStack("image_fragment")
                    .commit()
            }
        })

    }

    private fun setupRecycler() {
        recAdapter = RecAdapter { url -> setRecyclerEvent(url) }
        binding.recyclerViewImages.adapter = recAdapter
        binding.recyclerViewImages.layoutManager = LinearLayoutManager(this)
    }

    private fun setRecyclerEvent(url: String) {
        mainViewModel.setSingleRecyclerEvent(url)
    }
}