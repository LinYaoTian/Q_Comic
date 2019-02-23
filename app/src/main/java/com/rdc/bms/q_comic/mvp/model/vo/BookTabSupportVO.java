package com.rdc.bms.q_comic.mvp.model.vo;

import java.util.List;

public class BookTabSupportVO {
    private List<SupportItem> supportItemList;
    private List<Fan> fanList;

    @Override
    public String toString() {
        return "BookTabSupportVO{" +
                "supportItemList=" + supportItemList +
                ", fanList=" + fanList +
                '}';
    }

    public List<SupportItem> getSupportItemList() {
        return supportItemList;
    }

    public void setSupportItemList(List<SupportItem> supportItemList) {
        this.supportItemList = supportItemList;
    }

    public List<Fan> getFanList() {
        return fanList;
    }

    public void setFanList(List<Fan> fanList) {
        this.fanList = fanList;
    }

    public static class Fan{
        private String name;
        private String influence;
        private String coverUrl;

        @Override
        public String toString() {
            return "Fan{" +
                    "name='" + name + '\'' +
                    ", influence='" + influence + '\'' +
                    ", coverUrl='" + coverUrl + '\'' +
                    '}';
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name == null ? "" : name;
        }

        public String getInfluence() {
            return influence == null ? "" : influence;
        }

        public void setInfluence(String influence) {
            this.influence = influence == null ? "" : influence;
        }

        public String getCoverUrl() {
            return coverUrl == null ? "" : coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl == null ? "" : coverUrl;
        }
    }

    public static class SupportItem{
        private String option;
        private String num;
        private int iconResId;
        private int colorTextResId;
        private int drawableBgResId;

        @Override
        public String toString() {
            return "SupportItem{" +
                    "option='" + option + '\'' +
                    ", num='" + num + '\'' +
                    '}';
        }

        public int getDrawableBgResId() {
            return drawableBgResId;
        }

        public void setDrawableBgResId(int drawableBgResId) {
            this.drawableBgResId = drawableBgResId;
        }

        public int getColorTextResId() {
            return colorTextResId;
        }

        public void setColorTextResId(int colorTextResId) {
            this.colorTextResId = colorTextResId;
        }

        public int getIconResId() {
            return iconResId;
        }

        public void setIconResId(int iconResId) {
            this.iconResId = iconResId;
        }

        public String getOption() {
            return option == null ? "" : option;
        }

        public void setOption(String option) {
            this.option = option == null ? "" : option;
        }

        public String getNum() {
            return num == null ? "" : num;
        }

        public void setNum(String num) {
            this.num = num == null ? "" : num;
        }
    }
}
