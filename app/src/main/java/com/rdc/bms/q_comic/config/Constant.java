package com.rdc.bms.q_comic.config;

import java.security.PublicKey;

public class Constant {

    public static final String TIP_ERROR_GET_DATA = "获取信息出错！";

    public static final int TYPE_RANK = 0;//排行
    public static final int TYPE_BOOK_BILL = 1;//书单

    public static final int KIND_POPULARITY_LIST = 0;//人气榜
    public static final int KIND_REWARD_LIST = 1;//打赏榜
    public static final int KIND_MONTHLY_TICKET_LIST = 2;//月票榜

    public static final String TIP_NOT_COMPLETE = "这个功能还没有实现！";

    //Web端JSON Base
    public static final String BASE_URL_WEB_JSON = "http://api.zymk.cn/";
    //Web端Base地址
    public static final String BASE_URL_WEB = "https://m.zymk.cn/";
    //fiddler抓包Base地址
    public static final String BASE_URL_FIDDLER = "http://getconfig-globalapi.zymk.cn/app_api/v5/";
    //漫画封面Base地址start
    public static final String BASE_COMIC_COVER_START = "http://image.zymkcdn.com/file/cover/";
    //漫画封面Base地址end
    public static final String BASE_COMIC_COVER_END = ".jpg-300x400.webp";
    //获取书单（热门、推荐、最新）
    public static final String BOOK_BILL = "getbookpiazza_list";
    //请求BookList配置
    public static final String CLIENT_VERSION = "4.9.3";
    public static final String CLIENT_TYPE = "android";
    public static final String TYPE_SEARCH = "all";

    //搜索/获取分类地址
    public static final String SEARCH = "app_api/v5/getsortlist_new/";
    public static final String HOT_KEYS = "app_api/v5/gethotsearch/";
    public static final String COMIC_INFO = "app_api/v5/getcomicinfo/";
    public static final String COMIC = "app_api/v5/getcomic/";
    //具体的漫画里面每一页的图片url地址
    public static final String BASE_COMIC_IMAGE = "http://mhpic.zymkcdn.com/comic/";
    //书单内容数据
    public static final String BOOK_BILL_CONTAINER = "getbookinfo_user_center_body";
    //书单Head数据
    public static final String BOOK_BILL_HEAD = "getbookinfo_user_center_head";
    //书单详情封面Base地址start
    public static final String BASE_BOOK_BILL_COVER_START = "http://image.zymkcdn.com/file/book/";
    //书单详情封面Base地址end
    public static final String BASE_BOOK_BILL_COVER_END = ".jpg-noresize.webp";
    //作者头像Base地址start
    public static final String BASE_AUTHOR_IMAGE_START = "http://image.zymkcdn.com/file/head/";
    //作者头像Base地址end
    public static final String BASE_AUTHOR_IMAGE_END = ".jpg-100x100.jpg.webp";
}
