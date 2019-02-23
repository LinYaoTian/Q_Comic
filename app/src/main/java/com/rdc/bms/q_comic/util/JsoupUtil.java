package com.rdc.bms.q_comic.util;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.app.App;
import com.rdc.bms.q_comic.bean.BannerBean;
import com.rdc.bms.q_comic.bean.BookBean;
import com.rdc.bms.q_comic.bean.BookDetailVO;
import com.rdc.bms.q_comic.bean.ChapterBean;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabDetailVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabDirVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTabSupportVO;
import com.rdc.bms.q_comic.mvp.model.vo.BookTopInfoVO;
import com.rdc.bms.q_comic.bean.RankBean;
import com.rdc.bms.q_comic.bean.ItemBean;
import com.rdc.bms.q_comic.bean.TypeBean;
import com.rdc.bms.q_comic.mvp.model.dto.RankDTO;
import com.rdc.bms.q_comic.mvp.model.dto.HomePageDTO;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {


    /**
     * 漫画详情
     * @param e
     * @return
     */
    public static BookDetailVO toComicDetail(Element e){
        if (e == null){
            return null;
        }
        BookTopInfoVO topInfoBean = toComicTopInfo(e);
        BookTabDetailVO detailBean = toTabComicDetail(e);
        BookTabDirVO dirBean = toTabComicDir(e);
        BookTabSupportVO supportBean = tTabComicSupport(e);
        return new BookDetailVO(topInfoBean,detailBean,dirBean,supportBean);
    }

    /**
     * 漫画详情中 Tab=支持 的数据
     * @param doc
     * @return
     */
    private static BookTabSupportVO tTabComicSupport(Element doc) {
        if (doc == null){
            return null;
        }
        BookTabSupportVO supportBean = new BookTabSupportVO();
        List<BookTabSupportVO.Fan> fanList = new ArrayList<>();
        List<BookTabSupportVO.SupportItem> supportItemList = new ArrayList<>();
        //支持Tab
        int[] icons = new int[]{
                R.drawable.ic_ach_recommend,R.drawable.ic_ach_award,
                R.drawable.ic_ach_month,R.drawable.ic_ach_gradle,
                R.drawable.ic_ach_share,R.drawable.ic_ach_gift
        };
        int[] drawables = new int[]{
                R.drawable.bg_support_statistics_red,R.drawable.bg_support_statistics_orange,
                R.drawable.bg_support_statistics_green,R.drawable.bg_support_statistics_orange,
                R.drawable.bg_support_statistics_green,R.drawable.bg_support_statistics_red
        };
        int[] textColors = new int[]{
                R.color.bg_support_text_red,R.color.bg_support_text_orange,
                R.color.bg_support_text_green,R.color.bg_support_text_orange,
                R.color.bg_support_text_green,R.color.bg_support_text_red
        };
        Elements supportItemEles = doc.getElementsByClass("support").get(0).children();
        for (int i = 0; i < supportItemEles.size(); i++) {
            Element itemEle = supportItemEles.get(i);
            BookTabSupportVO.SupportItem item = new BookTabSupportVO.SupportItem();
            item.setNum(itemEle.child(1).childNode(1).childNode(0).toString()+itemEle.child(1).childNode(2).toString().replace("\n",""));
            item.setOption(itemEle.child(1).childNode(0).toString());
            item.setIconResId(icons[i % icons.length]);
            item.setDrawableBgResId(drawables[i % drawables.length]);
            item.setColorTextResId(textColors[i % textColors.length]);
            supportItemList.add(item);
        }
        //粉丝影响力
        //Top3
        int[] n = new int[]{2,1,3};
        for (int i = 0; i < n.length; i++) {
            String classI = "rank-" + n[i] + "st";
            if (doc.getElementsByClass(classI).size() != 0){
                Element top3Ele = doc.getElementsByClass(classI).get(0);
                BookTabSupportVO.Fan fan = new BookTabSupportVO.Fan();
                fan.setCoverUrl(top3Ele.child(0).child(0).attr("data-src"));
                fan.setInfluence("<font>影响力</font><font color=#F36653> "+top3Ele.child(2).child(0).text()+"</font>");
                fan.setName(top3Ele.child(1).text());
                fanList.add(fan);
            }
        }
        Elements items = doc.getElementsByClass("mk-influencerank-tabs").get(0)
                .getElementsByClass("item");
        if (items != null && items.size() > 0){
            for (Element oEle : items) {
                BookTabSupportVO.Fan fan = new BookTabSupportVO.Fan();
                fan.setCoverUrl(oEle.child(1).attr("data-src"));
                fan.setInfluence("<font color=#F36653>"+oEle.child(3).child(0).text()+" </font><font>影响力</font>");
                fan.setName(oEle.child(2).text());
                fanList.add(fan);
            }
        }

        supportBean.setFanList(fanList);
        supportBean.setSupportItemList(supportItemList);
        return supportBean;
    }

    /**
     * 漫画详情中 Tab=目录 的数据
     * @param doc
     * @return
     */
    private static BookTabDirVO toTabComicDir(Element doc) {
        if (doc == null){
            return null;
        }
        BookTabDirVO dirBean = new BookTabDirVO();
        dirBean.setUpdateTime(doc.getElementById("updateTime").text());
        List<ChapterBean> chapterList = new ArrayList<>();
        //目录
        Elements chapterEles= doc.getElementsByClass("chapterlist").get(0).children();
        for (int i = 0; i < chapterEles.size(); i++) {
            Element element = chapterEles.get(i);
            ChapterBean chapter = new ChapterBean();
            chapter.setChapterNum(element.getElementsByClass("ellipsis").get(0).text());
            chapter.setChapterId(Long.valueOf(element.attr("data-id")));
            chapterList.add(chapter);
        }
        dirBean.setChapterList(chapterList);
        return dirBean;
    }

    /**
     * 漫画详情顶部数据
     * @param doc
     * @return
     */
    private static BookTopInfoVO toComicTopInfo(Element doc){
        if (doc == null){
            return null;
        }
        BookTopInfoVO bean = new BookTopInfoVO();
        Element comicItem_thumbnail = doc.getElementsByClass("comic-item").get(0).child(0);

        bean.setCoverUrl(comicItem_thumbnail.child(0).attr("data-src"));
        bean.setScore(comicItem_thumbnail.child(1).text());
        Element span = doc.getElementsByClass("author").get(0);
        bean.setAuthor(span.text());
        bean.setName(doc.getElementsByClass("name").get(0).text());
        Elements tagEles = doc.getElementsByClass("tags");
        List<String> list = new ArrayList<>();
        for (Element tagEle : tagEles) {
            list.add(tagEle.child(0).text());
        }
        bean.setmTagList(list);
        bean.setCollected(false);
        bean.setFireNum(doc.getElementsByClass("comic-info").get(0).child(6).text());
        return bean;
    }

    /**
     * 漫画详情中 Tab=详情 的数据
     * @param doc
     * @return
     */
    private static BookTabDetailVO toTabComicDetail(Element doc) {
        BookTabDetailVO detailBean = new BookTabDetailVO();
        //详情
        Element detalTab = doc.getElementById("comicDetailTab");
        Element detalEle = detalTab.getElementsByClass("comic-detail").get(0);
        detailBean.setIntro(detalEle.child(1).text());
        Element authorEle = detalEle.child(3).child(0);
        detailBean.setAuthorCoverUrl(authorEle.child(0).attr("data-src"));
        detailBean.setAuthorName(authorEle.child(1).child(0).child(0).text());
        String fansNum = authorEle.child(1).child(1).child(0).text();
        detailBean.setPriorityResId(fansNum.length() > 3 ?
                R.drawable.svg_author_dk:R.drawable.svg_author_xr);
        fansNum = "<font>粉丝</font><font color=#F36653>"+fansNum+"</font><font>人</font>";
        detailBean.setFansNum(fansNum);
        detailBean.setNotice(detalEle.child(5).text());
        //作者其他作品
        //可能为null
        List<BookBean> otherWorkList = new ArrayList<>();
        Elements otherWorks = detalTab.getElementsByClass("comic-item");
        if (otherWorks != null) {
            for (Element itemEle : otherWorks) {
                BookBean bookBean = new BookBean();
                Element divEle = itemEle.child(0);
                bookBean.setRecentChapter(divEle.child(0).child(1).text());
                bookBean.setBookId(divEle.child(0).child(0).attr("data-id"));
                bookBean.setCoverUrl(divEle.child(0).child(0).attr("data-src"));
                bookBean.setScore(divEle.child(0).child(2).text());
                Element h3 = itemEle.child(1);
                bookBean.setName(h3.child(0).text());
                bookBean.setSummary(itemEle.child(2).text());
                otherWorkList.add(bookBean);
            }
        }
        detailBean.setAuthorOtherComicList(otherWorkList);

        List<BookBean> recommendComicList = new ArrayList<>();
        //相关推荐
        Elements itemEles = doc.getElementsByClass("mk-recommend").get(0).getElementsByClass("comic-item");
        if (itemEles != null){
            for (Element itemEle : itemEles) {
                BookBean bookBean = new BookBean();
                Element divEle = itemEle.child(0);
                bookBean.setRecentChapter(divEle.child(0).child(1).text());
                bookBean.setBookId(divEle.child(0).child(0).attr("data-id"));
                bookBean.setCoverUrl(divEle.child(0).child(0).attr("data-src"));
                bookBean.setScore(divEle.child(0).child(2).text());
                Element h3 = itemEle.child(1);
                bookBean.setName(h3.child(0).text());
                bookBean.setSummary(itemEle.child(2).text());
                recommendComicList.add(bookBean);
            }
        }
        detailBean.setRecommendComicList(recommendComicList);
        return detailBean;
    }

    /**
     * 获取分类标题
     * @param doc
     * @return
     */
    public static List<String> toKindTitleList(Element doc){
        if (doc == null){
            return null;
        }
        List<String> list = new ArrayList<>();
        Element rootEle = doc.getElementById("classTabs");
        Element headEle = rootEle.child(0);
        for (Element child : headEle.child(1).children()) {
            list.add(child.text());
        }
        return list;
    }

    /**
     * 获取某一分类下的TypeBean项
     * @param doc
     * @param kind 0:题材,1:进度,2:受众,3:媒体
     * @return
     */
    public static List<TypeBean> toTypeBeanList(Element doc,int kind){
        if (doc == null || kind > 3){
            return null;
        }
        List<TypeBean> list = new ArrayList<>();
        Element rootEle = doc.getElementById("classTabs");
        Element containerEle = rootEle.child(1).child(0);
        Element child = containerEle.child(kind);
        for (Element element : child.children()) {
            TypeBean typeBean = new TypeBean();
            Element e = element.child(0);
            typeBean.setName(e.attr("title"));
            typeBean.setTypeId(e.attr("href")
                            .replace("/sort/","")
                            .replace(".html",""));
            typeBean.setCoverUrl(e.child(0).child(0).attr("data-src"));
            list.add(typeBean);
        }
        return list;
    }

    /**
     * 获取某一排行榜的数据
     * @param doc
     * @param i 0为人气榜,1为打赏榜，2为月票榜
     * @return
     */
    public static List<RankBean> toRankList(Element doc, int i){
        if (doc == null || i > 2) {
            return null;
        }
        List<RankBean> comicList = new ArrayList<>();
        //获取内容(顺序：人气榜、打赏榜、月票榜)
        Element rootEle = doc.getElementById("rank-tabs");
        Element rankEle = rootEle.getElementsByClass("mk-rank-list").get(i);
        int[] iconGreyIds = new int[]{
                R.drawable.svg_fire_grey,
                R.drawable.svg_money_grey,
                R.drawable.svg_ticket_grey
        };
        int[] iconWhiteIds = new int[]{
                R.drawable.svg_fire_white,
                R.drawable.svg_money_white,
                R.drawable.svg_ticket_white
        };
        int j = 0;
        for (Element child : rankEle.children()) {
            RankBean bean = new RankBean();
            switch (j){
                case 0:
                    bean.setBg(R.color.bg_rank_1);
                    break;
                case 1:
                    bean.setBg(R.color.bg_rank_2);
                    break;
                case 2:
                    bean.setBg(R.color.bg_rank_3);
                    break;
                default:
                    bean.setBg(R.color.bg_rank_outer);
            }
            if (j < 3){
                bean.setTotalIvResId(iconWhiteIds[i]);
                bean.setColorName(App.getContext().getResources().getColor(R.color.colorWhite));
                bean.setColorRank(App.getContext().getResources().getColor(R.color.colorWhite));
            }else {
                bean.setTotalIvResId(iconGreyIds[i]);
                bean.setColorName(App.getContext().getResources().getColor(R.color.colorBlack));
                bean.setColorRank(App.getContext().getResources().getColor(R.color.colorLightGrey));
            }
            bean.setBookId(child.attr("data-id"));
            Element imageEle = child.child(0).child(0).child(0).child(0);
            bean.setImageUrl(imageEle.attr("data-src"));
            Element nameEle = child.child(1).child(0);
            bean.setName(nameEle.text());
            Elements tagEles = child.child(2).getElementsByClass("tags-txt");
            List<String> tagList = new ArrayList<>();
            for (Element tagEle : tagEles) {
                tagList.add(tagEle.text());
            }
            bean.setTagList(tagList);
            bean.setTotal(child.child(3).text());
            bean.setRank(""+(j+1));
            comicList.add(bean);
            j++;
        }
        return comicList;
    }


    public static RankDTO toComicRankDTO(Element doc) {
        if (doc == null) {
            return null;
        }
        List<RankBean> comicList = new ArrayList<>();
        List<String> kindList = new ArrayList<>();
        Element rootEle = doc.getElementById("rank-tabs");
        //获取Kind
        Elements titleEles = rootEle.getElementsByClass("item-warp");
        for (Element ele : titleEles) {
            kindList.add(ele.child(0).text());
        }
        //获取内容(顺序：人气榜、打赏榜、月票榜)
        Elements rankEles = rootEle.getElementsByClass("mk-rank-list");
        for (Element rankEle : rankEles) {
            List<String> tagList = new ArrayList<>();
            RankBean bean = new RankBean();
            for (Element child : rankEle.children()) {
                bean.setBookId(child.attr("data-id"));
                Element imageEle = child.child(0).child(0).child(0).child(0);
                bean.setImageUrl(imageEle.attr("data-src"));
                Element nameEle = child.child(1).child(0);
                bean.setName(nameEle.text());
                Elements tagEles = child.child(2).getElementsByClass("tags-txt");
                for (Element tagEle : tagEles) {
                    tagList.add(tagEle.text());
                }
                bean.setTagList(tagList);
                bean.setTotal(child.child(3).text());
            }
            comicList.add(bean);
        }
        return new RankDTO(kindList,comicList);
    }

    /**
     * 获取主页的数据
     * @param doc
     * @return
     */
    public static HomePageDTO toHomePageDTO(Element doc){
        HomePageDTO dto = null;
        if (doc == null){
            return null;
        }
        List<BannerBean> bannerBeanList = new ArrayList<>();
        List<ItemBean> itemBeanList = new ArrayList<>();
        Element bannerElement =  doc.getElementsByClass("swiper-wrapper").get(0);
        for (Element e : bannerElement.children()) {
            //轮播图
            BannerBean bannerBean = new BannerBean();
            bannerBean.setBookId(e.attr("href").replace("/",""));
            bannerBean.setImageUrl(e.attr("data-src"));
            bannerBean.setTitle(e.attr("title"));
            bannerBeanList.add(bannerBean);
        }
        List<Element> comicElements = doc.getElementsByClass("mk-floor");
        //分类漫画列表
        int i = 0;
        for (Element e: comicElements) {
            //分类名和简介
            ItemBean itemBean = new ItemBean();
            itemBean.setIconResId(ImageUtil.getHomeIconId(i));
            Element mainEle = e.child(0).child(0);
            itemBean.setTitle(mainEle.child(1).text());
            itemBean.setIntro(mainEle.child(2).text());
            if (e.child(0).children().size() > 1){
                Element aEle = e.child(0).child(1);
                itemBean.setMoreUrl(aEle.absUrl("href"));
            }else {
                itemBean.setMoreUrl("");
            }
            //分类内的漫画集合
            List<BookBean> bookBeanList = new ArrayList<>();
            for (Element e2 : e.getElementsByClass("swiper-wrapper").get(0).children()) {
                BookBean bookBean = new BookBean();
                Element comicEle = e2.child(0);
                Element divEle = comicEle.child(0).child(0);
                bookBean.setBookId(divEle.child(0).attr("data-id"));
                bookBean.setCoverUrl(divEle.child(0).attr("data-src"));
                bookBean.setScore(divEle.child(2).text());
                bookBean.setRecentChapter(divEle.child(1).text());
                Element h3Ele = comicEle.child(1);
                bookBean.setName(h3Ele.text());
                Element pEle = comicEle.child(2);
                bookBean.setSummary(pEle.text());
                bookBeanList.add(bookBean);
            }
            itemBean.setBookBeanList(bookBeanList);
            itemBeanList.add(itemBean);
            i++;
        }
        dto = new HomePageDTO(bannerBeanList,itemBeanList);
        return dto;
    }


}
