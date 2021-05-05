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
import com.example.databindingtest.adapter.ImageLoadStateAdapter
import com.example.databindingtest.adapter.PagingRecAdapter
import com.example.databindingtest.databinding.ActivityImagesBinding
import com.example.databindingtest.repository.PixaRepository

class ImagesActivity : AppCompatActivity() {

    lateinit var binding: ActivityImagesBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var pagingRecAdapter: PagingRecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        setupPagingRecycler()
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PixaRepository())
        ).get(MainViewModel::class.java)

        mainViewModel.photos.observe(this, Observer {
            pagingRecAdapter.submitData(this.lifecycle, it)
        })

        mainViewModel.getSingleRecyclerEvent().observe(this, Observer { url ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.constraintLayoutImages, ImageFragment.newInstance(url))
                .addToBackStack("image_fragment")
                .commit()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewItem = menu?.findItem(R.id.action_search)
        val actionSearchView = searchViewItem?.actionView as SearchView
        actionSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty()) {
                        binding.recyclerViewImages.scrollToPosition(0)
                        mainViewModel.searchPhotos(query)
                        actionSearchView.clearFocus()
                    }
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupPagingRecycler() {
        pagingRecAdapter = PagingRecAdapter { url -> setRecyclerEvent(url) }
        binding.recyclerViewImages.adapter = pagingRecAdapter.withLoadStateHeaderAndFooter(
            header = ImageLoadStateAdapter { pagingRecAdapter.retry() },
            footer = ImageLoadStateAdapter { pagingRecAdapter.retry() }
        )
        binding.recyclerViewImages.setHasFixedSize(true)
        binding.recyclerViewImages.layoutManager = LinearLayoutManager(this)
    }

    private fun setRecyclerEvent(url: String) {
        mainViewModel.setSingleRecyclerEvent(url)
    }
}