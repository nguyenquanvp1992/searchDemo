package com.quannv.searchapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.quannv.searchapplication.base.BaseFragment
import com.quannv.searchapplication.databinding.FragmentMainBinding
import com.quannv.searchapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var binding: FragmentMainBinding? = null
    private var adapter: ImageAdapter = ImageAdapter(mutableListOf())
    private var currentPage: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.list?.adapter = adapter
        handleInputSearch()
        handleViewModel()
        handleLoadMore()
    }

    private fun handleViewModel() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            if (it.page == 1L) {
                adapter.updateData(it.photos)
            } else {
                adapter.addData(it.photos)
            }
            currentPage = it.page
            binding?.progressBar?.isVisible = false
        }
        viewModel.isShowLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it
        }
    }

    private fun handleInputSearch() {
        binding?.inputKeySearch?.doAfterTextChanged {
            it?.toString()?.let { key ->
                if (key.length >= 2) {
                    viewModel.search(key)
                }
            }
        }
    }

    private fun handleLoadMore() {
        binding?.list?.addOnScrollListener(object: OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && adapter.canLoadMore()) {
                    viewModel.nextPage(binding?.inputKeySearch?.text?.toString() ?: "", currentPage + 1)
                }
            }
        })
    }
}