package com.rdc.bms.q_comic.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 漫画实体
 */
public class BookBean extends LitePalSupport {

    private String name;//漫画名
    private String coverUrl;//漫画封面URL
    @Column(unique = true)
    private String bookId;//漫画ID
    private String summary;//摘要
    private String score;//评分
    private long firstOpenLastChapterId;//第一次打开本漫画时的最新章节ID
    private String recentChapter;//最新话数
    private long recentChapterId;//最新话ID
    private String lastRecordChapter;//已读话数
    private long lastRecordChapterId;//已读话ID
    @Column(defaultValue = "0")
    private boolean isUpdate;//是否有更新
    @Column(ignore = true)
    private boolean isCollect;//是否收藏
    @Column(ignore = true)
    private boolean isSelect;

    @Override
    public String toString() {
        return "BookBean{" +
                "name='" + name + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", summary='" + summary + '\'' +
                ", score='" + score + '\'' +
                ", firstOpenLastChapterId=" + firstOpenLastChapterId +
                ", recentChapter='" + recentChapter + '\'' +
                ", recentChapterId=" + recentChapterId +
                ", lastRecordChapter='" + lastRecordChapter + '\'' +
                ", lastRecordChapterId=" + lastRecordChapterId +
                ", isUpdate=" + isUpdate +
                ", isCollect=" + isCollect +
                ", isSelect=" + isSelect +
                '}';
    }

    public long getFirstOpenLastChapterId() {
        return firstOpenLastChapterId;
    }

    public void setFirstOpenLastChapterId(long firstOpenLastChapterId) {
        this.firstOpenLastChapterId = firstOpenLastChapterId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public long getRecentChapterId() {
        return recentChapterId;
    }

    public void setRecentChapterId(long recentChapterId) {
        this.recentChapterId = recentChapterId;
    }

    public long getLastRecordChapterId() {
        return lastRecordChapterId;
    }

    public void setLastRecordChapterId(long lastRecordChapterId) {
        this.lastRecordChapterId = lastRecordChapterId;
    }

    public long getPrimaryKey(){
        return getBaseObjId();
    }

    public String getLastRecordChapter() {
        return lastRecordChapter == null ? "" : lastRecordChapter;
    }

    public void setLastRecordChapter(String lastRecordChapter) {
        this.lastRecordChapter = lastRecordChapter == null ? "" : lastRecordChapter;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public void setRecentChapter(String recentChapter) {
        this.recentChapter = recentChapter == null ? "" : recentChapter;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getRecentChapter() {
        return recentChapter;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getCoverUrl() {
        return coverUrl == null ? "" : coverUrl;
    }

    public void setCoverUrl(String imageUrl) {
        this.coverUrl = imageUrl == null ? "" : imageUrl;
    }

    public String getBookId() {
        return bookId == null ? "" : bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? "" : bookId;
    }

    public String getSummary() {
        return summary == null ? "" : summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? "" : summary;
    }

    public String getScore() {
        return score == null ? "" : score;
    }

    public void setScore(String score) {
        this.score = score == null ? "" : score;
    }
}
