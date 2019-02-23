package com.rdc.bms.q_comic.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 书单
 */
public class BookBillBean extends LitePalSupport {
    private ArrayList<String> bookIdList;
    private String title;
    private int num;
    private String intro;
    @Column(unique = true)
    private String bookBillId;

    public long getPrimaryKey(){
        return getBaseObjId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBillBean that = (BookBillBean) o;
        return Objects.equals(bookBillId, that.bookBillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookBillId);
    }

    @Override
    public String toString() {
        return "BookBillBean{" +
                "bookIdList=" + bookIdList.toString() +
                ", title='" + title + '\'' +
                ", num=" + num +
                ", intro='" + intro + '\'' +
                ", bookBillId='" + bookBillId + '\'' +
                '}';
    }

    public ArrayList<String> getBookIdList() {
        return bookIdList;
    }

    public void setBookIdList(ArrayList<String> bookIdList) {
        this.bookIdList = bookIdList;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIntro() {
        return intro == null ? "" : intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? "" : intro;
    }

    public String getBookBillId() {
        return bookBillId == null ? "" : bookBillId;
    }

    public void setBookBillId(String bookBillId) {
        this.bookBillId = bookBillId == null ? "" : bookBillId;
    }
}
