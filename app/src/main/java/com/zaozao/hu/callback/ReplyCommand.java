package com.zaozao.hu.callback;

import android.view.View;
import android.widget.AdapterView;

import io.reactivex.functions.Action;

/**
 * Created by 胡章孝
 * Date:2018/1/15
 * Describle:
 */

public class ReplyCommand {

    private Action action;

    public ReplyCommand(Action action) {
        this.action = action;

    }

    public void execue() {
        if (action != null) {
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
