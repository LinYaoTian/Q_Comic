package com.rdc.bms.easy_rv_adapter.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;

public abstract class BaseRvCell<T> implements Cell {

    public T mData;

    public OnClickViewRvListener mListener;

    public final BaseRvViewHolder createSimpleHolder(ViewGroup parent,int layoutResId){
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(layoutResId,parent,false));
    }

    @Override
    public long getItemId(int position) {
        return RecyclerView.NO_ID;
    }

    /**
     * 添加监听事件
     * @param mListener
     */
    public void setListener(OnClickViewRvListener mListener) {
        this.mListener = mListener;
    }

    public BaseRvCell(T t){
        mData = t;
    }
}
