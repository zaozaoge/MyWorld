package com.zaozao.hu.viewmodel

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.zaozao.hu.BR
import com.zaozao.hu.R
import com.zaozao.hu.model.MainItem
import me.tatarka.bindingcollectionadapter2.ItemBinding


/**
 * Created by 胡章孝
 * Date:2018/1/4
 * Describle:
 */
class MainViewModel {


    val items: ObservableList<MainItem> = ObservableArrayList<MainItem>()

    val itemBinding: ItemBinding<MainItem> = ItemBinding.of(BR.item, R.layout.item_main_list)
    val viewStyle: ViewStyle = ViewStyle()
    var context: Context? = null

    constructor(context: Context) {
        items.add(MainItem("我是谁", 1, 1))
        items.add(MainItem("我是谁", 2, 2))
        items.add(MainItem("我是谁", 3, 3))
        items.add(MainItem("我是谁", 4, 4))
        this.context = context
        itemBinding.bindExtra(BR.listener, listener)
    }

    val listener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(item: MainItem) {
            if (item.itemId == 1)
                ARouter.getInstance().build("/account/view").navigation()
            else
                Toast.makeText(context, "开发中，敬请期待!", Toast.LENGTH_LONG).show()
        }
    }

    class ViewStyle {
        val isRefreshing: ObservableBoolean = ObservableBoolean(true)
        val progressRefreshing: ObservableBoolean = ObservableBoolean(true)
    }

    // val listener:AdapterView.OnItemClickListener = AdapterView.OnItemClickListener(::onItemClick)
    interface OnItemClickListener {
        fun onItemClick(item: MainItem)
    }

}