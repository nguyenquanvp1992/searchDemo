package com.quannv.searchapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.quannv.searchapplication.base.BaseFragment
import com.quannv.searchapplication.databinding.FragmentDetailBinding
import com.quannv.searchapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment: BaseFragment<MainViewModel>() {

    companion object {
        fun newInstance() = ImageDetailFragment()
    }

    private var binding: FragmentDetailBinding? = null
    private var adapter: ImageDetailAdapter = ImageDetailAdapter(mutableListOf())
    private var currentPage: Long = 0
    private var currentSelect: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPage()
        handleViewModel()
        handleDelete()
    }

    private fun handleViewModel() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.addData(it.photos)
            currentPage = it.page
        }
    }

    private fun setupViewPage() {
        binding?.viewPager?.adapter = adapter
        binding?.viewPager?.offscreenPageLimit = 3
        binding?.viewPager?.registerOnPageChangeCallback(object: OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentSelect = position
                if (position >= adapter.itemCount - 2) {
                    viewModel.nextPage(currentPage + 1)
                }
            }
        })
    }

    private fun handleDelete() {
        binding?.actionDelete?.setOnClickListener {
            adapter.removeItem(currentSelect)
        }
    }
}