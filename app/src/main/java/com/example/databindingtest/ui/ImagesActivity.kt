package com.example.databindingtest.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
            supportFragmentManager.apply {
                popBackStack()
                beginTransaction()
                    .replace(R.id.constraintLayoutImages, ImageFragment.newInstance(url))
                    .addToBackStack("image_fragment")
                    .commit()
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewItem = menu?.findItem(R.id.action_search)
        val actionSearchView = searchViewItem?.actionView as SearchView
        actionSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty())
                        mainViewModel.searchPhotos(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
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