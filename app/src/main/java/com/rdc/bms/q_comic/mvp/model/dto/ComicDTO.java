package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.ChapterBean;

import java.util.List;

public class ComicDTO {

    private int status;
    private String msg;
    private List<Data> data;

    public boolean isSuccess(){
        return "ok".equals(msg) || status == 0;
    }

    /**
     * 获取最新一章
     * @return
     */
    public ChapterBean toLastChapter(){
        if (isSuccess()){
            ChapterBean chapterBean = new ChapterBean();
            chapterBean.setChapterNum(data.get(0).getLast_chapter().getName());
            chapterBean.setChapterId(Long.valueOf(data.get(0).getLast_chapter().getId()));
            return chapterBean;
        }else{
            return null;
        }

    }


    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public class Comic_type {

        private int id;
        private String name;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }

    public class Last_chapter {

        private String id;
        private String name;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

    }

    public class Data {

        private long comicid;
        private String comicname;
        private List<Comic_type> comic_type;
        private String comic_feature;
        private String comic_tag;
        private int authorid;
        private String authorname;
        private Last_chapter last_chapter;
        private long renqi;
        private double pingfen;
        private long pingfen_count;
        private long shoucang;
        private long tuijian;
        private long share;
        private long dashang;
        private long yuepiao;
        private long zongpiao;
        private int status;
        private int price;
        private int readtype;
        public void setComicid(long comicid) {
            this.comicid = comicid;
        }
        public long getComicid() {
            return comicid;
        }

        public void setComicname(String comicname) {
            this.comicname = comicname;
        }
        public String getComicname() {
            return comicname;
        }

        public void setComic_type(List<Comic_type> comic_type) {
            this.comic_type = comic_type;
        }
        public List<Comic_type> getComic_type() {
            return comic_type;
        }

        public void setComic_feature(String comic_feature) {
            this.comic_feature = comic_feature;
        }
        public String getComic_feature() {
            return comic_feature;
        }

        public void setComic_tag(String comic_tag) {
            this.comic_tag = comic_tag;
        }
        public String getComic_tag() {
            return comic_tag;
        }

        public void setAuthorid(int authorid) {
            this.authorid = authorid;
        }
        public int getAuthorid() {
            return authorid;
        }

        public void setAuthorname(String authorname) {
            this.authorname = authorname;
        }
        public String getAuthorname() {
            return authorname;
        }

        public void setLast_chapter(Last_chapter last_chapter) {
            this.last_chapter = last_chapter;
        }
        public Last_chapter getLast_chapter() {
            return last_chapter;
        }

        public void setRenqi(long renqi) {
            this.renqi = renqi;
        }
        public long getRenqi() {
            return renqi;
        }

        public void setPingfen(double pingfen) {
            this.pingfen = pingfen;
        }
        public double getPingfen() {
            return pingfen;
        }

        public void setPingfen_count(long pingfen_count) {
            this.pingfen_count = pingfen_count;
        }
        public long getPingfen_count() {
            return pingfen_count;
        }

        public void setShoucang(long shoucang) {
            this.shoucang = shoucang;
        }
        public long getShoucang() {
            return shoucang;
        }

        public void setTuijian(long tuijian) {
            this.tuijian = tuijian;
        }
        public long getTuijian() {
            return tuijian;
        }

        public void setShare(long share) {
            this.share = share;
        }
        public long getShare() {
            return share;
        }

        public void setDashang(long dashang) {
            this.dashang = dashang;
        }
        public long getDashang() {
            return dashang;
        }

        public void setYuepiao(long yuepiao) {
            this.yuepiao = yuepiao;
        }
        public long getYuepiao() {
            return yuepiao;
        }

        public void setZongpiao(long zongpiao) {
            this.zongpiao = zongpiao;
        }
        public long getZongpiao() {
            return zongpiao;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }

        public void setPrice(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }

        public void setReadtype(int readtype) {
            this.readtype = readtype;
        }
        public int getReadtype() {
            return readtype;
        }

    }
}
