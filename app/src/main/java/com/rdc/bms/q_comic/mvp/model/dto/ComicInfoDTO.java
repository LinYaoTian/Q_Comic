package com.rdc.bms.q_comic.mvp.model.dto;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.util.ImageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComicInfoDTO {

    private int status;
    private String msg;
    private Data data;

    public boolean isSuccess(){
        return "ok".equals(msg) || status == 0;
    }

    /**
     * 获取漫画章节（正序从第一话 -> 最终话）
     * @return
     */
    public List<ChapterBean> toChapterList(){
        if (!isSuccess()){
            return null;
        }
        List<ChapterBean> list = new ArrayList<>();
        int size = data.getChapter_list().size();//原本是逆序的（从最后章节到第一章）
        Collections.reverse(data.getChapter_list());
        for (int i = 0; i < size; i++) {
            Chapter_list chapter = data.getChapter_list().get(i);
            ChapterBean bean = new ChapterBean();
            ChapterBean.ComicImage comicImage = new ChapterBean.ComicImage();
            comicImage.setHigh(chapter.getChapter_image().getHigh());
            comicImage.setMiddle(chapter.getChapter_image().getMiddle());
            comicImage.setLow(chapter.getChapter_image().getLow());
            bean.setBookId(String.valueOf(data.getComic_id()));
            bean.setNextChapterId(i == (size - 1)?ChapterBean.NO_CHAPTER_ID:data.getChapter_list().get(i+1).getChapter_id());
            bean.setPreChapterId(i == 0 ?ChapterBean.NO_CHAPTER_ID:data.getChapter_list().get(i-1).getChapter_id());
            bean.setComicImage(comicImage);
            bean.setStartVal(chapter.getStart_var());
            bean.setEndVal(chapter.getEnd_var());
            bean.setCoverUrl(ImageUtil.toComicImageUrl(chapter.getChapter_image().getHigh(), bean.getStartVal()));
            bean.setFree(chapter.getPrice() == 0);
            bean.setName(chapter.getChapter_title());
            bean.setChapterNum(chapter.getChapter_name());
            bean.setChapterId(chapter.getChapter_id());
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

    public class Chapter_image {

        private String low;
        private String middle;
        private String high;
        public void setLow(String low) {
            this.low = low;
        }
        public String getLow() {
            return low;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }
        public String getMiddle() {
            return middle;
        }

        public void setHigh(String high) {
            this.high = high;
        }
        public String getHigh() {
            return high;
        }

    }

    public class Data {

        private List<Chapter_list> chapter_list;
        private long comic_id;

        public long getComic_id() {
            return comic_id;
        }

        public void setComic_id(long comic_id) {
            this.comic_id = comic_id;
        }

        public void setChapter_list(List<Chapter_list> chapter_list) {
            this.chapter_list = chapter_list;
        }
        public List<Chapter_list> getChapter_list() {
            return chapter_list;
        }

    }

    public class Chapter_list {

        private long chapter_id;
        private String chapter_name;
        private String chapter_title;
        private long create_time;
        private String chapter_addr;
        private int start_var;
        private int end_var;
        private int price;
        private int chapter_type;
        private String image_suffix;
        private String chapter_copyright;
        private int download_price;
        private Chapter_image chapter_image;
        private String webview;
        public void setChapter_id(long chapter_id) {
            this.chapter_id = chapter_id;
        }
        public long getChapter_id() {
            return chapter_id;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }
        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_title(String chapter_title) {
            this.chapter_title = chapter_title;
        }
        public String getChapter_title() {
            return chapter_title;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }
        public long getCreate_time() {
            return create_time;
        }

        public void setChapter_addr(String chapter_addr) {
            this.chapter_addr = chapter_addr;
        }
        public String getChapter_addr() {
            return chapter_addr;
        }

        public void setStart_var(int start_var) {
            this.start_var = start_var;
        }
        public int getStart_var() {
            return start_var;
        }

        public void setEnd_var(int end_var) {
            this.end_var = end_var;
        }
        public int getEnd_var() {
            return end_var;
        }

        public void setPrice(int price) {
            this.price = price;
        }
        public int getPrice() {
            return price;
        }

        public void setChapter_type(int chapter_type) {
            this.chapter_type = chapter_type;
        }
        public int getChapter_type() {
            return chapter_type;
        }

        public void setImage_suffix(String image_suffix) {
            this.image_suffix = image_suffix;
        }
        public String getImage_suffix() {
            return image_suffix;
        }

        public void setChapter_copyright(String chapter_copyright) {
            this.chapter_copyright = chapter_copyright;
        }
        public String getChapter_copyright() {
            return chapter_copyright;
        }

        public void setDownload_price(int download_price) {
            this.download_price = download_price;
        }
        public int getDownload_price() {
            return download_price;
        }

        public void setChapter_image(Chapter_image chapter_image) {
            this.chapter_image = chapter_image;
        }
        public Chapter_image getChapter_image() {
            return chapter_image;
        }

        public void setWebview(String webview) {
            this.webview = webview;
        }
        public String getWebview() {
            return webview;
        }

    }
}
