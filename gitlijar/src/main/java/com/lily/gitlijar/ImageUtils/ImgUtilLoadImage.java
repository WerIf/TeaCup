package com.lily.gitlijar.ImageUtils;

import android.content.Context;
import android.media.VolumeShaper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImgUtilLoadImage implements ImgUtilLoadInterface {


    @Override
    public void ImageLoadImage(Context mContext, ImageView mImageView, String url) {
        Glide.with(mContext)
                .load(url)
                .into(mImageView);
    }

    @Override
    public void ImageLoadImage(Context mContext, ImageView mImageView, String url, int mErrorPath,ImgUtilsType loadType) {

        RequestOptions mOptions=new RequestOptions();
        switch (loadType.getValue()){
            case 0:
                mOptions.error(mErrorPath);
                break;
            case 1:
                mOptions.placeholder(mErrorPath);
                break;
        }

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }

    @Override
    public void ImageLoadImage(Context mContext, ImageView mImageView, String url, int mErrorPath, int placeHolderPath) {

        RequestOptions mOptions=new RequestOptions()
                .error(mErrorPath)
                .placeholder(placeHolderPath);

        Glide.with(mContext)
                .load(url)
                .apply(mOptions)
                .into(mImageView);
    }
}
