package com.lily.gitlijar.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lily.gitlijar.ImageUtils.ImageLoadInterface;
import com.lily.gitlijar.ImageUtils.ImageLoadUtils;
import com.lily.gitlijar.ImageUtils.ImgUtilsType;

public class GitliViewHolder extends RecyclerView.ViewHolder {
    private final View mItemView;
    public TextView mIndexView;
    private ImageLoadInterface utilLoadImage;

    public GitliViewHolder(@NonNull View itemView){
        super(itemView);
        mItemView=itemView;
    }

    public GitliViewHolder(@NonNull View itemView,int indexViewId) {
        super(itemView);
        mItemView = itemView;
        mIndexView =(TextView)itemView.findViewById(indexViewId);
    }

    public <T extends View> T getViewFromId(int resourceId, Class<T> typeClass) {
        T mView = mItemView.findViewById(resourceId);
        return mView;
    }

    public void setTextToView(int resourceId, String text) {
        TextView mTextView = mItemView.findViewById(resourceId);
        mTextView.setText(text);
    }

    public void setImageToView(Context mContext, int resourceId, String url) {
        ImageView mImageView = mItemView.findViewById(resourceId);

        if (utilLoadImage==null){
            utilLoadImage=ImageLoadUtils.getInstance();
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url);
    }

    public void setImageToView(Context mContext, int resourceId, String url, int errorOrPlacePath, ImgUtilsType type) {
        ImageView mImageView = mItemView.findViewById(resourceId);

        if (utilLoadImage==null){
            utilLoadImage=ImageLoadUtils.getInstance();
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url,errorOrPlacePath,type);
    }

    public void setImageToView(Context mContext, int resourceId, String url,int errorPath,int placePath) {
        ImageView mImageView = mItemView.findViewById(resourceId);

        if (utilLoadImage==null){
            utilLoadImage=ImageLoadUtils.getInstance();
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url,errorPath,placePath);
    }
}
