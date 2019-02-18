package com.lily.gitlijar.ImageUtils;

import android.content.Context;
import android.widget.ImageView;

public interface ImgUtilLoadInterface {

    void ImageLoadImage(Context mContext, ImageView mImageView, String url);

    void ImageLoadImage(Context mContext, ImageView mImageView, String url, int mErrorOrPlacePath,ImgUtilsType loadType);

    void ImageLoadImage(Context mContext, ImageView mImageView, String url, int mErrorPath, int placeHolderPath);
}
