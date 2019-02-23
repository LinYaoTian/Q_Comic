package com.rdc.bms.q_comic.util;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

import com.rdc.bms.q_comic.R;
import com.rdc.bms.q_comic.config.Constant;

public class ImageUtil {

    public static final int[] iconResIds = new int[]{
            R.mipmap.svg_pic_list_dream,R.mipmap.svg_pic_list_fast,
            R.mipmap.svg_pic_list_fire,R.mipmap.svg_pic_list_jd,
            R.mipmap.svg_pic_list_love,R.mipmap.svg_pic_list_new
    };

    public static int getHomeIconId(int position){
        int p = position % iconResIds.length;
        return iconResIds[p];
    }


    /**
     *  漫画中每章具体的图片
     * @param imagePath
     * @param page
     * @return
     */
    public static String toComicImageUrl(String imagePath, int page){
        String s = Constant.BASE_COMIC_IMAGE + imagePath;
        return s.replace("$$",String.valueOf(page));
    }

    /**
     * 根据漫画ID获取漫画封面URL
     * @param bookId
     * @return
     */
    public static String getCoverUrl(int bookId){
        return getCoverUrl(String.valueOf(bookId));
    }

    public static String getCoverUrl(String bookId){
        StringBuilder sb = new StringBuilder(bookId);
        while (sb.length() < 9){
            sb.insert(0,"0");
        }
        char[] chars = sb.toString().toCharArray();
        sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (i == 2 || i == 5){
                sb.append("/");
            }
        }
        return Constant.BASE_COMIC_COVER_START + sb.toString() + Constant.BASE_COMIC_COVER_END;
    }

    /**
     * 根据书单详情ID获取书单内的封面URL
     * @param bookBillId
     * @return
     */
    public static String toBookBillCoverUrl(String bookBillId){
        StringBuilder sb = new StringBuilder(bookBillId);
        while (sb.length() < 9){
            sb.insert(0,"0");
        }
        char[] chars = sb.toString().toCharArray();
        sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (i == 2 || i == 5){
                sb.append("/");
            }
        }
        return Constant.BASE_BOOK_BILL_COVER_START + sb.toString() + Constant.BASE_BOOK_BILL_COVER_END;
    }

    /**
     * 改变图片的亮度
     * @param imageView
     * @param brightness [-255,0]时为暗
     */
    public static void changeBrightness(ImageView imageView, int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        imageView.setColorFilter(new ColorMatrixColorFilter(cMatrix));
    }

    /**
     * 获取作者头像
     */
    public static String toAuthorImage(String authorId){
        StringBuilder sb = new StringBuilder(authorId);
        while (sb.length() < 9){
            sb.insert(0,"0");
        }
        char[] chars = sb.toString().toCharArray();
        sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (i == 2 || i == 5){
                sb.append("/");
            }
        }
        return Constant.BASE_AUTHOR_IMAGE_START + sb.toString() + Constant.BASE_AUTHOR_IMAGE_END;
    }
}
