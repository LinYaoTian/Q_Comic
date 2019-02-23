package com.rdc.bms.easy_rv_adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.R;
import com.rdc.bms.easy_rv_adapter.base.BaseRvStateCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.easy_rv_adapter.config.ItemType;

public class LoadingCell extends BaseRvStateCell {

    public LoadingCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return ItemType.LOADING_TYPE;
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_loading_layout,null);
    }
}
