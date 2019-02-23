package com.rdc.bms.q_comic.bean;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class ChapterBean extends LitePalSupport implements Cloneable {

    @Column(ignore = true)
    public final static int NO_CHAPTER_ID = -2;
    private int startVal;//内容漫画开始页
    private int endVal;//内容漫画结束页
    private ComicImage comicImage;
    private String coverUrl;//封面
    private String name;//章节名
    private String chapterNum;//属于第几章
    private boolean isFree;//是否收费
    private boolean isRead;//是否阅读过
    @Column(nullable = false)
    private String bookId;
    @Column(nullable = false,unique = true)
    private long chapterId;
    private long nextChapterId;
    private long preChapterId;
    @Column(ignore = true)
    private boolean isChecked = false;//是否被选中
    @Column(ignore = true)
    private boolean hasRedPoint;

    public boolean isHasRedPoint() {
        return hasRedPoint;
    }

    public void setHasRedPoint(boolean hasRedPoint) {
        this.hasRedPoint = hasRedPoint;
    }

    @Override
    public ChapterBean clone(){
        ChapterBean c = null;
        try {
            c = (ChapterBean) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return c;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getBookId() {
        return bookId == null ? "" : bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? "" : bookId;
    }

    public static long getNoChapterId() {
        return NO_CHAPTER_ID;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getNextChapterId() {
        return nextChapterId;
    }

    public void setNextChapterId(long nextChapterId) {
        this.nextChapterId = nextChapterId;
    }

    public long getPreChapterId() {
        return preChapterId;
    }

    public void setPreChapterId(long preChapterId) {
        this.preChapterId = preChapterId;
    }

    public ComicImage getComicImage() {
        return comicImage;
    }

    public void setComicImage(ComicImage comicImage) {
        this.comicImage = comicImage;
    }

    public int getStartVal() {
        return startVal;
    }

    public void setStartVal(int startVal) {
        this.startVal = startVal;
    }

    public int getEndVal() {
        return endVal;
    }

    public void setEndVal(int endVal) {
        this.endVal = endVal;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getChapterId() {
        return chapterId;
    }

    public String getCoverUrl() {
        return coverUrl == null ? "" : coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? "" : coverUrl;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getChapterNum() {
        return chapterNum == null ? "" : chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum == null ? "" : chapterNum;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public static class ComicImage extends LitePalSupport {

        private ChapterBean chapterBean;//数据库外键
        //画质
        private String low;
        private String middle;
        private String high;

        public ChapterBean getChapterBean() {
            return chapterBean;
        }

        public void setChapterBean(ChapterBean chapterBean) {
            this.chapterBean = chapterBean;
        }

        public String getLow() {
            return low == null ? "" : low;
        }

        public void setLow(String low) {
            this.low = low == null ? "" : low;
        }

        public String getMiddle() {
            return middle == null ? "" : middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle == null ? "" : middle;
        }

        public String getHigh() {
            return high == null ? "" : high;
        }

        public void setHigh(String high) {
            this.high = high == null ? "" : high;
        }
    }
}
