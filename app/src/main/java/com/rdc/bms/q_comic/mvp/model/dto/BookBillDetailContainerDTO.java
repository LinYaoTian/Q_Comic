package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class BookBillDetailContainerDTO {

        private int status;
        private String msg;
        private Data data;

        public boolean isSuccess(){
            return status == 0;
        }

        public List<BookBean> toBookList(){
            if (!isSuccess()){
                return new ArrayList<>();
            }
            List<BookBean> list = new ArrayList<>();
            for (Book_list book : data.getBook_list()) {
                BookBean bean = new BookBean();
                bean.setBookId(String.valueOf(book.getComic_id()));
                bean.setCoverUrl(ImageUtil.getCoverUrl(book.getComic_id()));
                bean.setName(book.getComic_name());
                bean.setRecentChapter(book.getLast_chapter().getName());
                bean.setSummary(book.getComic_feature());
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

    public class Page {

        private int total_num;
        private int page_size;
        private int total_page;
        private int current_page;
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
    public class Book_list {

        private int comic_id;
        private String comic_name;
        private String comic_feature;
        private Last_chapter last_chapter;
        private boolean disable;
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

        public void setDisable(boolean disable) {
            this.disable = disable;
        }
        public boolean getDisable() {
            return disable;
        }

    }
    public class Data {

        private Page page;
        private List<Book_list> book_list;
        public void setPage(Page page) {
            this.page = page;
        }
        public Page getPage() {
            return page;
        }

        public void setBook_list(List<Book_list> book_list) {
            this.book_list = book_list;
        }
        public List<Book_list> getBook_list() {
            return book_list;
        }

    }
}

