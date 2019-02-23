package com.rdc.bms.q_comic.util;

import android.content.Context;
import android.content.Intent;

import com.rdc.bms.q_comic.mvp.view.activity.BookBillDetailActivity;
import com.rdc.bms.q_comic.mvp.view.activity.BookDetailActivity;
import com.rdc.bms.q_comic.mvp.view.activity.BookBillActivity;
import com.rdc.bms.q_comic.mvp.view.activity.ComicActivity;
import com.rdc.bms.q_comic.mvp.view.activity.MainActivity;
import com.rdc.bms.q_comic.mvp.view.activity.SearchActivity;
import com.rdc.bms.q_comic.mvp.view.activity.SearchResultActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 对startActivity的封装
 */
public class StartActUtil {

    public static void toBookBillDetail(Context context, String bookBillId, ArrayList<String> coverBookIdList){
        Intent intent = new Intent(context,BookBillDetailActivity.class);
        intent.putExtra("bookBillId",bookBillId);
        intent.putStringArrayListExtra("coverBookIdList",coverBookIdList);
        context.startActivity(intent);
    }

    public static void toBookDetail(Context context, String bookId){
        Intent intent = new Intent(context,BookDetailActivity.class);
        intent.putExtra("bookId",bookId);
        context.startActivity(intent);
    }

    public static void toSearchAct(Context context){
        context.startActivity(new Intent(context,SearchActivity.class));
    }

    public static void toMainAct(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

    public static void toComicAct(Context context, long chapterId,String bookId){
        Intent intent = new Intent(context,ComicActivity.class);
        intent.putExtra("chapterId",chapterId);
        intent.putExtra("bookId",bookId);
        context.startActivity(intent);
    }

    public static void toBookBillAct(Context context, int type){
        Intent intent = new Intent(context,BookBillActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void toSearchResultAct(Context context, String title, String key, String type){
        Intent intent = new Intent(context,SearchResultActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("key",key);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void toSearchResultAct(Context context, String title,String type){
        Intent intent = new Intent(context,SearchResultActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
}
