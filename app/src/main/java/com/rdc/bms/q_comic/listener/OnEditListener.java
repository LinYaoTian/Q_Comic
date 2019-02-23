package com.rdc.bms.q_comic.listener;

public interface OnEditListener {
    /**
     * 开始编辑
     */
    void onStartEdit();

    /**
     * 编辑结束
     */
    void onEndEdit();

    /**
     * 全选或取消全选
     * @param isSelect
     */
    void onSelectAll(boolean isSelect);

    /**
     * 点击删除
     */
    void onDelete();
}
