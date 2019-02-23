package com.rdc.bms.q_comic.bean.rv_cell;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.base.BaseBookShelfCell;
import com.rdc.bms.q_comic.base.BaseBookShelfVO;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.config.ItemType;
import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;

public class CollectCell extends BaseBookShelfCell<CollectVO> {
    public CollectCell(CollectVO collectVO) {
        super(collectVO);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_SUBSCRIBE;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createSimpleHolder(parent, R.layout.cell_book_collect);
    }

    @Override
    public void onBindViewHolder(final BaseRvViewHolder holder, final int position) {
        super.onBindViewHolder(holder,position);
        TextView tvIsUpdated = holder.getTextView(R.id.tv_is_update_cell_book_collect);
        TextView tvName = holder.getTextView(R.id.tv_name_cell_book_collect);
        TextView tvRecentChapter = holder.getTextView(R.id.tv_recent_cell_book_collect);
        TextView tvRecordChapter = holder.getTextView(R.id.tv_record_cell_book_collect);
        ImageView ivCover = holder.getImageView(R.id.iv_cover_cell_book_collect);

        tvIsUpdated.setVisibility(mData.getBookBean().isUpdate()?View.VISIBLE:View.GONE);
        tvName.setText(mData.getBookBean().getName());
        tvRecentChapter.setText(mData.getBookBean().getRecentChapter());
        tvRecordChapter.setText(mData.getBookBean().getLastRecordChapter());
        Glide.with(holder.getContext()).load(mData.getBookBean().getCoverUrl()).into(ivCover);
    }
}
