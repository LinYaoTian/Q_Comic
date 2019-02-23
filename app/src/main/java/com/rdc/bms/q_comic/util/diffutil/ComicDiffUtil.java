package com.rdc.bms.q_comic.util.diffutil;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.rdc.bms.q_comic.bean.ComicBean;

import java.util.List;

public class ComicDiffUtil extends DiffUtil.Callback{

    private List<ComicBean> mOldDatas,mNewDatas;

    public ComicDiffUtil(List<ComicBean> mOldDatas, List<ComicBean> mNewDatas) {
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
        return mNewDatas.get(newItemPosition).getComicId()
                .equals(mOldDatas.get(oldItemPosition).getComicId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewDatas.get(newItemPosition).getImageUrl()
                .equals(mOldDatas.get(oldItemPosition).getImageUrl());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
