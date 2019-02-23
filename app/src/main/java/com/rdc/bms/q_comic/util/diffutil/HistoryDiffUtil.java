package com.rdc.bms.q_comic.util.diffutil;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.rdc.bms.q_comic.mvp.model.vo.CollectVO;
import com.rdc.bms.q_comic.mvp.model.vo.HistoryVO;

import java.util.List;

public class HistoryDiffUtil extends DiffUtil.Callback {
    private List<HistoryVO> mOldDatas,mNewDatas;

    public HistoryDiffUtil(List<HistoryVO> mOldDatas, List<HistoryVO> mNewDatas) {
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
        return mNewDatas.get(newItemPosition).getBookBean().getBookId()
                .equals(mOldDatas.get(oldItemPosition).getBookBean().getBookId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        HistoryVO newItem = mNewDatas.get(newItemPosition);
        HistoryVO oldItem = mOldDatas.get(oldItemPosition);
        return oldItem.isStartSelect() == newItem.isStartSelect()
                && oldItem.isSelect() == newItem.isSelect()
                && newItem.getBookBean().getLastRecordChapterId() == (oldItem.getBookBean().getLastRecordChapterId())
                && newItem.getBookBean().getRecentChapter().equals((oldItem.getBookBean().getRecentChapter()));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
