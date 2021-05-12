package com.example.databindingtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.databindingtest.R
import com.example.databindingtest.databinding.FragmentImageBinding

private const val IMAGE_URL = "image_url"

class ImageFragment : Fragment() {

    lateinit var binding: FragmentImageBinding
    private lateinit var imageFragmentViewModel: ImageFragmentViewModel
    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(IMAGE_URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageFragmentViewModel = ViewModelProvider(this, ImageFragmentViewModelFactory(url)).get(ImageFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false)
        binding.imageFragmentViewModel = imageFragmentViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageFragmentViewModel.getImageClickSingleEvent().observe(viewLifecycleOwner, Observer {
            fragmentManager?.popBackStack()
        })
    }

    companion object {
        fun newInstance(url: String): ImageFragment {
            val args = Bundle()
            args.putString(IMAGE_URL, url)
            val fragment = ImageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}