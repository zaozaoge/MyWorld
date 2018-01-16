package com.zaozao.hu.view.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 胡章孝
 * Date:2018/1/16
 * Describle:
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
