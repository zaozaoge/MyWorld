package com.zaozao.hu.module_library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author 胡章孝
 * @date 2017/2/20
 * @time 12:56
 * @desc 通用ViewHolder
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "ViewHolder";
    private SparseArray<View> mViews;


    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = null;
        try {
            itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        } catch (Exception e) {
            Log.e(TAG, "资源文件未找到");
        }
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    /**
     * 获取子控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param s
     */
    public void setText(int viewId, String s) {
        TextView textView = getView(viewId);
        textView.setText(s);
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param s
     * @param textColor
     */
    public void setText(int viewId, String s, int textColor) {
        TextView textView = getView(viewId);
        textView.setText(s);
        textView.setTextColor(textColor);
    }
}
