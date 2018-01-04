package com.zaozao.hu.account

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zaozao.hu.account.view.base.BaseActivity

@Route(path = "/view/MainActivity")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity_main)
    }
}
