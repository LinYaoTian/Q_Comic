package com.rdc.bms.easy_rv_adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rdc.bms.easy_rv_adapter.R;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvStateCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.easy_rv_adapter.config.ItemType;

public class EmptyCell extends BaseRvStateCell {

    public EmptyCell(Object o) {
        super(o);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.EMPTY_TYPE;
    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_empty_layout,null);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }
}
