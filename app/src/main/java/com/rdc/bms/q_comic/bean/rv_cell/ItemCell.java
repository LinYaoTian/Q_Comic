package com.rdc.bms.q_comic.bean.rv_cell;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.easy_rv_adapter.base.RvSimpleAdapter;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.ItemBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.rdc.bms.q_comic.listener.OnComicClickListener;
import com.rdc.bms.q_comic.util.StartActUtil;

public class ItemCell extends BaseRvCell<ItemBean> {

    private String TAG = "ItemCell";

    public ItemCell(ItemBean itemBean) {
        super(itemBean);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_BOOK_LIST;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, int position) {
        ImageView ivIcon = holder.getImageView(R.id.iv_icon_cell_item);
        TextView tvTitle = holder.getTextView(R.id.tv_title_cell_item);
        TextView tvIntro = holder.getTextView(R.id.tv_intro_cell_item);
        RecyclerView rvBookList = (RecyclerView) holder.getView(R.id.rv_book_cell_item);

        Log.d(TAG, "onBindViewHolder->  mContext="+holder.getContext());
        Glide.with(holder.getContext()).load(mData.getIconResId()).into(ivIcon);
        tvTitle.setText(mData.getTitle());
        tvIntro.setText(mData.getIntro());
        if (rvBookList.getAdapter() == null){
            rvBookList.setLayoutManager(new LinearLayoutManager(
                    holder.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
            rvBookList.setAdapter(new RvSimpleAdapter());
        }
        RvSimpleAdapter simpleAdapter = (RvSimpleAdapter) rvBookList.getAdapter();
        simpleAdapter.clear();
        simpleAdapter.addAll(mData.getComicCellList(new OnComicClickListener() {
            @Override
            public void onClick(String comicId) {
                StartActUtil.toBookDetail(holder.getContext(),comicId);
            }
        }));
    }
}
