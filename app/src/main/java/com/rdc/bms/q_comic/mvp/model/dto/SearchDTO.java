package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchDTO {

    private int status;
    private String msg;
    private Data data;

    public boolean isSuccess(){
        return "ok".equals(msg);
    }

    public PageDTO toPageDTO(){
        if (!isSuccess()){
            return null;
        }
        return new PageDTO(
                data.getPage().getTotal_page(),
                data.getPage().getCurrent_page());
    }

    public List<BookBean> toComicList(){
        if (!isSuccess()){
            return null;
        }
        List<BookBean> list = new ArrayList<>();
        for (Comic_list comic : data.getPage().comic_list) {
            BookBean bean = new BookBean();
            bean.setBookId(String.valueOf(comic.getComic_id()));
            bean.setCoverUrl(ImageUtil.getCoverUrl(comic.getComic_id()));
            bean.setName(comic.getComic_name());
            bean.setSummary(comic.getComic_feature());
            bean.setScore(String.valueOf(((int)(comic.getScore()))/10)+"分");
            bean.setRecentChapter(comic.getLast_chapter().getName());
            list.add(bean);
        }
        return list;
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

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public class Type {

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

    public class Comic_list {

        private int comic_id;
        private String comic_name;
        private String comic_feature;
        private Last_chapter last_chapter;
        private double score;
        public void setComic_id(int comic_id) {
            this.comic_id = comic_id;
        }
        public int getComic_id() {
            return comic_id;
        }

        public void setComic_name(String comic_name) {
            this.comic_name = comic_name;
        }
        public String getComic_name() {
            return comic_name;
        }

        public void setComic_feature(String comic_feature) {
            this.comic_feature = comic_feature;
        }
        public String getComic_feature() {
            return comic_feature;
        }

        public void setLast_chapter(Last_chapter last_chapter) {
            this.last_chapter = last_chapter;
        }
        public Last_chapter getLast_chapter() {
            return last_chapter;
        }

        public void setScore(double score) {
            this.score = score;
        }
        public double getScore() {
            return score;
        }

    }

    public class Page {

        private int total_num;
        private int page_size;
        private int total_page;
        private int current_page;
        private List<Comic_list> comic_list;
        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }
        public int getTotal_num() {
            return total_num;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }
        public int getPage_size() {
            return page_size;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }
        public int getTotal_page() {
            return total_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }
        public int getCurrent_page() {
            return current_page;
        }

        public void setComic_list(List<Comic_list> comic_list) {
            this.comic_list = comic_list;
        }
        public List<Comic_list> getComic_list() {
            return comic_list;
        }

    }

    public class Data {

        private Type type;
        private String key;
        private String sort;
        private Page page;
        public void setType(Type type) {
            this.type = type;
        }
        public Type getType() {
            return type;
        }

        public void setKey(String key) {
            this.key = key;
        }
        public String getKey() {
            return key;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setPage(Page page) {
            this.page = page;
        }
        public Page getPage() {
            return page;
        }

    }
}
