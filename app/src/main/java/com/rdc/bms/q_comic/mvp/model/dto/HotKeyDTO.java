package com.rdc.bms.q_comic.mvp.model.dto;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.bean.HotKeyBean;

import java.util.ArrayList;
import java.util.List;

public class HotKeyDTO {

    private int status;
    private String msg;
    private Data data;

    private static final int[] mColorResId = new int[]{
            R.drawable.bg_hot_key_purple,R.drawable.bg_hot_key_light_green,
            R.drawable.bg_hot_key_dark_green,R.drawable.bg_hot_key_red,
            R.drawable.bg_hot_key_orange,R.drawable.bg_hot_key_blue
    };

    public boolean isSuccess(){
        return "ok".equals(msg);
    }

    public List<HotKeyBean> toHotKeyBeanList(){
        if (!isSuccess()){
            return null;
        }
        List<HotKeyBean> list = new ArrayList<>();
        int  i = 0;
        for (MyList myList : data.getList()) {
            HotKeyBean keyBean = new HotKeyBean();
            keyBean.setKey(myList.getComic_name());
            keyBean.setColorResId(mColorResId[i % mColorResId.length]);
            list.add(keyBean);
            i++;
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

    public class MyList {

        private int comic_id;
        private String comic_name;
        private Last_chapter last_chapter;
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

        public void setLast_chapter(Last_chapter last_chapter) {
            this.last_chapter = last_chapter;
        }
        public Last_chapter getLast_chapter() {
            return last_chapter;
        }

    }

    public class Data {

        private List<MyList> list;
        public void setList(List<MyList> list) {
            this.list = list;
        }
        public List<MyList> getList() {
            return list;
        }

    }
}
