package com.rdc.bms.q_comic.bean;

import android.view.View;

import com.rdc.bms.easy_rv_adapter.OnClickViewRvListener;
import com.rdc.bms.easy_rv_adapter.base.BaseRvCell;
import com.rdc.bms.q_comic.bean.rv_cell.BookCell;
import com.rdc.bms.q_comic.listener.OnComicClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页分类漫画列表实体
 */
public class ItemBean {

    private String title;//标题
    private String intro;//分类简介
    private String moreUrl;//加载更多Url
    private List<BookBean> bookBeanList;//漫画列表
    private int iconResId;//图标本地ID

    private List<BaseRvCell> comicCellList;

    @Override
    public String toString() {
        return "ItemBean{" +
                "title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", moreUrl='" + moreUrl + '\'' +
                ", bookBeanList=" + bookBeanList +
                ", iconResId=" + iconResId +
                '}';
    }

    public List<BaseRvCell> getComicCellList(final OnComicClickListener listener) {
        if (comicCellList == null
                && bookBeanList != null){
            comicCellList = new ArrayList<>();
            for (final BookBean bookBean : bookBeanList) {
                BookCell bookCell = new BookCell(bookBean);
                bookCell.setListener(new OnClickViewRvListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public <C> void onClickItem(C data, int position) {
                        if (listener != null){
                            listener.onClick(bookBean.getBookId());
                        }
                    }
                });
                comicCellList.add(bookCell);
            }
        }
        return comicCellList;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public String getIntro() {
        return intro == null ? "" : intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? "" : intro;
    }

    public String getMoreUrl() {
        return moreUrl == null ? "" : moreUrl;
    }

    public void setMoreUrl(String moreUrl) {
        this.moreUrl = moreUrl == null ? "" : moreUrl;
    }

    public List<BookBean> getBookBeanList() {
        return bookBeanList;
    }

    public void setBookBeanList(List<BookBean> bookBeanList) {
        this.bookBeanList = bookBeanList;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
