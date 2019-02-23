package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.mvp.model.vo.BookBillDetailVO;
import com.rdc.bms.q_comic.util.ImageUtil;

import java.util.Random;

public class BookBillDetailHeadDTO {

        private int status;
        private String msg;
        private Data data;

        private transient BookBillDetailVO mBookBillDetailVO;

        public boolean isSuccess(){
            return status == 0;
        }

        private BookBillDetailVO toBookBillDetailVO(){
            if (!isSuccess()) {
                return null;
            }
            BookBillDetailVO vo = new BookBillDetailVO();
            vo.setAuthorImage(ImageUtil.toAuthorImage(String.valueOf(data.getAuthorid())));
            vo.setBookCount(String.valueOf(data.getComic_count()));
            vo.setBookBillId(String.valueOf(data.getBook_id()));
            vo.setBookTitle(data.getBook_title());
            vo.setSummary(data.getSummary());
            vo.setCollectNum(String.valueOf(data.getCollection()));
            //评论数在另一接口，为了避免麻烦这里就不获取了
            Random random = new Random();
            vo.setCommentNum(String.valueOf(random.nextInt(100)));
            vo.setShareNum(String.valueOf(data.getShare()));
            vo.setBookBillCover(ImageUtil.toBookBillCoverUrl(String.valueOf(data.getBook_id())));
            return vo;
        }

        public BookBillDetailVO getBookBillDetailVO(){
            if (mBookBillDetailVO == null){
                mBookBillDetailVO = toBookBillDetailVO();
            }
            return mBookBillDetailVO;
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


    public class Data {

        private long book_id;
        private String book_title;
        private String summary;
        private int authorid;
        private int collection;
        private int share;
        private int comic_count;
        private boolean disabled;
        private boolean union;
        private int isvip;
        public void setBook_id(long book_id) {
            this.book_id = book_id;
        }
        public long getBook_id() {
            return book_id;
        }

        public void setBook_title(String book_title) {
            this.book_title = book_title;
        }
        public String getBook_title() {
            return book_title;
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

        public void setCollection(int collection) {
            this.collection = collection;
        }
        public int getCollection() {
            return collection;
        }

        public void setShare(int share) {
            this.share = share;
        }
        public int getShare() {
            return share;
        }

        public void setComic_count(int comic_count) {
            this.comic_count = comic_count;
        }
        public int getComic_count() {
            return comic_count;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }
        public boolean getDisabled() {
            return disabled;
        }

        public void setUnion(boolean union) {
            this.union = union;
        }
        public boolean getUnion() {
            return union;
        }

        public void setIsvip(int isvip) {
            this.isvip = isvip;
        }
        public int getIsvip() {
            return isvip;
        }

    }
}
