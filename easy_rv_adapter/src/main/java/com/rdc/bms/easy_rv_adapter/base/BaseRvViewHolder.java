package com.rdc.bms.easy_rv_adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseRvViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View mItemView;

    public BaseRvViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        mItemView = itemView;
    }

    public Context getContext(){
        return mItemView.getContext();
    }

    /**
     * 获取 ItemView (这里指的是整个Item项)
     * @return
     */
    public View getItemView(){
        return mItemView;
    }

    public View getView(int resId){
        return retrieveView(resId);
    }

    public TextView getTextView(int resId){
        return retrieveView(resId);
    }

    public ImageView getImageView(int resId){
        return retrieveView(resId);
    }

    public Button getButton(int resId){
        return retrieveView(resId);
    }

    public void setText(int resId,CharSequence text){
        getTextView(resId).setText(text);
    }

    public void setText(int resId,int strId){
        getTextView(resId).setText(strId);
    }

    @SuppressWarnings("unchecked")
    protected <V extends View> V retrieveView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = mItemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (V) view;
    }


}
