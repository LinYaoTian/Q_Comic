package com.rdc.bms.q_comic.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.easy_rv_adapter.base.Cell;
import com.rdc.bms.q_comic.R;

public abstract class BaseBookShelfCell<T extends BaseBookShelfVO> extends BaseRvCell<T> {


    public BaseBookShelfCell(T t) {
        super(t);
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        View selectLayout = holder.getView(R.id.layout_select_cell);
        final ImageView ivSelect = holder.getImageView(R.id.iv_select_layout_select);
        if (mData.isStartSelect()){
            selectLayout.setVisibility(View.VISIBLE);
            Glide.with(holder.getContext())
                    .load(mData.isSelect()?R.drawable.svg_red_select : R.drawable.svg_white_select)
                    .into(ivSelect);
            if (mListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                selectLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mData.setSelect(!mData.isSelect());
                        Glide.with(holder.getContext())
                                .load(mData.isSelect()?R.drawable.svg_red_select : R.drawable.svg_white_select)
                                .into(ivSelect);
                        mListener.onClick(v,position);
                    }
                });
            }
        }else {
            selectLayout.setVisibility(View.GONE);
            if (mListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onClickItem(mData,position);
                    }
                });
            }
        }
    }
}
