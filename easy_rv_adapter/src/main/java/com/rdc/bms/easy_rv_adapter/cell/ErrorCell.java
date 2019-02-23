package com.rdc.bms.easy_rv_adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.rdc.bms.easy_rv_adapter.R;
import com.rdc.bms.easy_rv_adapter.base.BaseRvStateCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.easy_rv_adapter.config.ItemType;

public class ErrorCell extends BaseRvStateCell {

    public ErrorCell(Object o) {
        super(o);
    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_error_layout,null);
    }

    @Override
    public int getItemType() {
        return ItemType.ERROR_TYPE;
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {

    }
}
