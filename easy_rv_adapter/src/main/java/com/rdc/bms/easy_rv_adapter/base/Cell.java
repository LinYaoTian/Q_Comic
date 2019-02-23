package com.rdc.bms.easy_rv_adapter.base;

import android.view.ViewGroup;

public interface Cell {

    long getItemId(int position);

    /**
     * 回收资源
     */
     void releaseResource();

    /**
     * 获取 viewType
     * @return
     */
    int getItemType();

    /**
     * 创建 ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    void onBindViewHolder(BaseRvViewHolder holder, int position);

}
