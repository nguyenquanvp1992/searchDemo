package com.quannv.searchapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.quannv.searchapplication.MainActivity
import com.quannv.searchapplication.R
import com.quannv.searchapplication.databinding.FragmentMainBinding
import com.quannv.searchapplication.viewModel.MainViewModel

class MainFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    private var binding: FragmentMainBinding? = null
    private var currentPage: Long = 0
    private var adapter: ImageAdapter = ImageAdapter(mutableListOf(), onItemClick = {
        (activity as? MainActivity)?.getNavController()?.navigate(R.id.detailFragment)
    })

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
}