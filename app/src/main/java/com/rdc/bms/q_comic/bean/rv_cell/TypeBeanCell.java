package com.rdc.bms.q_comic.bean.rv_cell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.TypeBean;
import com.rdc.bms.q_comic.config.ItemType;

public class TypeBeanCell extends BaseRvCell<TypeBean> {
    public TypeBeanCell(TypeBean typeBean) {
        super(typeBean);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BOOK;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_type,parent,false));
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        holder.getTextView(R.id.tv_name_cell_type).setText(mData.getName());
        Glide.with(holder.getContext())
                .load(mData.getCoverUrl())
                .into(holder.getImageView(R.id.iv_cover_cell_type));
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
