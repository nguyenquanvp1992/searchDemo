package com.quannv.searchapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.quannv.searchapplication.databinding.FragmentMainBinding
import com.quannv.searchapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private var binding: FragmentMainBinding? = null
    private var adapter: ImageAdapter = ImageAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.list?.adapter = adapter
        handleInputSearch()
        handleViewModel()
    }

    private fun handleViewModel() {

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