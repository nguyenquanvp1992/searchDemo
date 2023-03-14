package com.quannv.searchapplication.base

import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<T: BaseViewModel>: Fragment() {

    @Inject
    lateinit var viewModel: T
}