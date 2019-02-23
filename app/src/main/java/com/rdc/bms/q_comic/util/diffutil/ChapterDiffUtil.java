package com.rdc.bms.q_comic.util.diffutil;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.bean.ComicBean;

import java.util.List;

public class ChapterDiffUtil extends DiffUtil.Callback{

    private List<ChapterBean> mOldDatas,mNewDatas;

    public ChapterDiffUtil(List<ChapterBean> mOldDatas, List<ChapterBean> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas == null?0:mOldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return mNewDatas==null?0:mNewDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewDatas.get(newItemPosition).getChapterId()
                == (mOldDatas.get(oldItemPosition).getChapterId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewDatas.get(newItemPosition).isRead() ==
                mOldDatas.get(oldItemPosition).isRead();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
