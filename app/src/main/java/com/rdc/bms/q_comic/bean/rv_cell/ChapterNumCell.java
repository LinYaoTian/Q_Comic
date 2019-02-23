package com.rdc.bms.q_comic.bean.rv_cell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.easy_rv_adapter.base.BaseRvViewHolder;
import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.config.ItemType;

/**
 * 单纯只有话数
 */
public class ChapterNumCell extends BaseRvCell<ChapterBean> {

    public ChapterNumCell(ChapterBean chapter) {
        super(chapter);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public int getItemType() {
        return ItemType.TYPE_CHAPTER;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRvViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_chapter_num,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, final int position) {
        TextView tv = holder.getTextView(R.id.tv_num_cell_chapter_num);
        ImageView ivRead = holder.getImageView(R.id.iv_is_read_cell_chapter_num);
        ImageView ivUnRead = holder.getImageView(R.id.iv_un_read_cell_chapter_num);
        ivUnRead.setVisibility(mData.isHasRedPoint() && !mData.isRead()?View.VISIBLE:View.GONE);
        ivRead.setVisibility(mData.isRead()?View.VISIBLE:View.GONE);
        tv.setText(mData.getChapterNum());
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
