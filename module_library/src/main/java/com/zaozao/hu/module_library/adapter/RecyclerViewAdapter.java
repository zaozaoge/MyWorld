package com.zaozao.hu.module_library.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author 胡章孝
 * @date 2017/2/15
 * @time 15:39
 * @desc 通用RecyclerView适配器
 * 继承此类，重写bindData方法即可实现数据的绑定
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements OnClickListener {

    protected Activity activity;
    private List<T> data;
    private int layoutId;
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    private View header;
    private View footer;
    private String TAG = "RecyclerViewAdapter";
    private RecyclerItemClickListener listener;
    private RecyclerView recyclerView;

    public RecyclerViewAdapter(Activity activity, int layoutId, List<T> data, RecyclerItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.layoutId = layoutId;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(footer);
        } else if (viewType == TYPE_HEADER) {
            return new ViewHolder(header);
        } else {
            return ViewHolder.get(activity, parent, layoutId);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!isHeadView(position) && !isFooterView(position)) {
            if (header != null) position--;
            holder.itemView.setTag(data.get(position));
            holder.itemView.setOnClickListener(this);
            bindData(holder, data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int count = data == null ? 0 : data.size();
        if (header != null) {
            count++;
        }
        if (footer != null)
            count++;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeadView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (this.recyclerView == null || this.recyclerView != recyclerView) {
            this.recyclerView = recyclerView;
        }
        ifGridLayoutManager();
    }

    /**
     * 是否是header
     *
     * @param position
     * @return
     */
    private boolean isHeadView(int position) {
        return (position == 0 && header != null);
    }

    /**
     * 是否是footer
     *
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return position == getItemCount() - 1 && footer != null;
    }

    /**
     * 添加header
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (header != null) {
            throw new IllegalStateException("已经添加了头部");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            header = view;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }
    }

    /**
     * 添加footer
     *
     * @param view
     */
    public void addFooterView(View view) {
        if (footer != null) {
            throw new IllegalStateException("已经添加了尾部");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            footer = view;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    /**
     * 如果是表格布局则需特殊处理
     */
    private void ifGridLayoutManager() {
        if (recyclerView == null) {
            return;
        }
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isFooterView(position) || isHeadView(position)) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onRecyclerViewItemClick(v);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param t
     */
    public abstract void bindData(ViewHolder holder, T t);

    public interface RecyclerItemClickListener {
        void onRecyclerViewItemClick(View v);
    }
}
