package com.zaozao.hu.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.zaozao.hu.R
import com.zaozao.hu.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
        //  ARouter.getInstance().build("").navigation()
    }

    private fun init() {
        val gridLayoutManager = GridLayoutManager(this, 3)
        rv_main_fun.layoutManager = gridLayoutManager
    }

}
