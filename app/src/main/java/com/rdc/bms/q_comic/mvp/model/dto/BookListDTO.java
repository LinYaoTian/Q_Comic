package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.bean.BookBillBean;

import java.util.ArrayList;
import java.util.List;

public class BookListDTO {

    private int status;
    private String msg;
    private Data data;

    private transient List<BookBillBean> mBookBillBeanList;

    @Override
    public String toString() {
        return "BookListDTO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return "ok".equals(msg);
    }

    public PageDTO getPageBean() {
        PageDTO pageDTO = new PageDTO();
        if (!isSuccess()) {
            return null;
        }
        pageDTO.setCurrentPage(data.getPage().getCurrent_page());
        pageDTO.setTotalPage(data.getPage().getTotal_page());
        return pageDTO;
    }

    private List<BookBillBean> toBookListBeanList() {
        List<BookBillBean> list = new ArrayList<>();
        if (!isSuccess()) {
            return list;
        }
        for (Book_list bookList : data.getBook_list()) {
            BookBillBean bean = new BookBillBean();
            ArrayList<String> bookIdList = new ArrayList<>();
            for (Integer integer : bookList.getComics()) {
                bookIdList.add(String.valueOf(integer));
            }
            bean.setBookIdList(bookIdList);
            bean.setBookBillId(String.valueOf(bookList.getBook_id()));
            bean.setTitle(bookList.getTitle());
            bean.setIntro(bookList.getSummary());
            bean.setNum(bookList.getComic_num());
            list.add(bean);
        }
        return list;
    }

    public List<BookBillBean> getBookListBeanList(){
        if (mBookBillBeanList == null){
            mBookBillBeanList = toBookListBeanList();
        }
        return mBookBillBeanList;
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

    public class Book_list {

        private long book_id;
        private String book_name;
        private int collection_num;
        private int comic_num;
        private String title;
        private String keyword;
        private String summary;
        private int authorid;
        private int share;
        private int shoucang;
        private int comicnum;
        private int ordernum;
        private long createtime;
        private long updatetime;
        private int status;
        private List<Integer> comics;
        private String Uname;
        private int iscollect;

        public void setBook_id(long book_id) {
            this.book_id = book_id;
        }

        public long getBook_id() {
            return book_id;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setCollection_num(int collection_num) {
            this.collection_num = collection_num;
        }

        public int getCollection_num() {
            return collection_num;
        }

        public void setComic_num(int comic_num) {
            this.comic_num = comic_num;
        }

        public int getComic_num() {
            return comic_num;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSummary() {
            return summary;
        }

        public void setAuthorid(int authorid) {
            this.authorid = authorid;
        }

        public int getAuthorid() {
            return authorid;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getShare() {
            return share;
        }

        public void setShoucang(int shoucang) {
            this.shoucang = shoucang;
        }

        public int getShoucang() {
            return shoucang;
        }

        public void setComicnum(int comicnum) {
            this.comicnum = comicnum;
        }

        public int getComicnum() {
            return comicnum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setUpdatetime(long updatetime) {
            this.updatetime = updatetime;
        }

        public long getUpdatetime() {
            return updatetime;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setComics(List<Integer> comics) {
            this.comics = comics;
        }

        public List<Integer> getComics() {
            return comics;
        }

        public void setUname(String Uname) {
            this.Uname = Uname;
        }

        public String getUname() {
            return Uname;
        }

        public void setIscollect(int iscollect) {
            this.iscollect = iscollect;
        }

        public int getIscollect() {
            return iscollect;
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
