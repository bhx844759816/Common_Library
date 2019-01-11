package com.bhx.library.common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView得适配器
 */
public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private LayoutInflater mInflater;
    private List<T> mData;

    public BaseRvAdapter(Context context, List<T> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = mInflater.inflate(getLayoutId(), viewGroup, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int pos) {
        convert(viewHolder, pos, mData.get(pos));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 获取布局id
     *
     * @return
     */
    abstract int getLayoutId();

    /**
     * 必须复写
     *
     * @param holder
     * @param pos
     * @param t
     */
    abstract void convert(BaseViewHolder holder, int pos, T t);
}
