package com.zaozao.hu.databinding;

import android.databinding.BindingAdapter;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.zaozao.hu.callback.ReplyCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 胡章孝
 * Date:2018/1/15
 * Describle:
 */

public final class ViewBindingAdapter {

    @BindingAdapter({"clickCommand"})
    public static void clickCommand(View view, final Action replyCommand) {
        RxView.clicks(view).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {
                        if (replyCommand != null) {
                            replyCommand.run();
                        }
                    }
                });
    }
}
