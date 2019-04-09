package com.july.teacup.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.july.teacup.ImageUtils.TeaCupImage;
import com.july.teacup.ImageUtils.imgbasics.ImageBasice;
import com.july.teacup.ImageUtils.load.GlideImageLoadInterface;
import com.july.teacup.ImageUtils.load.ImageLoadUtils;
import com.july.teacup.ImageUtils.imgbasics.ImgUtilsType;

public class GitliViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final View mItemView;
    public TextView mIndexView;
    private GlideImageLoadInterface utilLoadImage;
    private OnBackViewHolder mBackViewHolder;

    /**
     *  普通Item
     * @param itemView
     */
    public GitliViewHolder(@NonNull View itemView){
        super(itemView);
        mItemView=itemView;
        mItemView.setOnClickListener(this);
    }

    /**
     *  当加入快速查找列时 使用的构造器
     * @param itemView
     * @param indexViewId   字母显示列
     */
    public GitliViewHolder(@NonNull View itemView,int indexViewId) {
        super(itemView);
        mItemView = itemView;
        mIndexView =(TextView)itemView.findViewById(indexViewId);
    }


    public void registerClickEvent(OnBackViewHolder backViewHolder){
        this.mBackViewHolder=backViewHolder;
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
            utilLoadImage=TeaCupImage.getService(ImageLoadUtils.class);
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url);
    }

    public void setImageToView(Context mContext, int resourceId, String url, int errorOrPlacePath, ImgUtilsType type) {
        ImageView mImageView = mItemView.findViewById(resourceId);

        if (utilLoadImage==null){
            utilLoadImage=TeaCupImage.getService(ImageLoadUtils.class);
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url,errorOrPlacePath,type);
    }

    public void setImageToView(Context mContext, int resourceId, String url,int errorPath,int placePath) {
        ImageView mImageView = mItemView.findViewById(resourceId);

        if (utilLoadImage==null){
            utilLoadImage=TeaCupImage.getService(ImageLoadUtils.class);
        }

        utilLoadImage.ImageLoad(mContext,mImageView,url,errorPath,placePath);
    }

    @Override
    public void onClick(View v) {

        mBackViewHolder.clickViewHolder(v, (Integer) mItemView.getTag());
    }
}
