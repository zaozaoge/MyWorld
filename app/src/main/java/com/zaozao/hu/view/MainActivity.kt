package com.zaozao.hu.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.zaozao.hu.R
import com.zaozao.hu.databinding.ActivityMainBinding
import com.zaozao.hu.view.base.BaseActivity
import com.zaozao.hu.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(this)
        init()
    }

    private fun init() {

    }

}
