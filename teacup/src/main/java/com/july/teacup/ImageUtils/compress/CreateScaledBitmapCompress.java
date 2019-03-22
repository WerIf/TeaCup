package com.july.teacup.ImageUtils.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.july.teacup.ImageUtils.CompressStandard;

/**
 *      创建期望大小Bitmap
 *      将图片压缩成用户所期望的的长度和宽度,如果用户期望的的长度和宽度和原图长度宽度相差太
 *      多的话,图片会很不清晰
 */
public class CreateScaledBitmapCompress extends CompressBasics implements ImageCompressInterface {
    @Override
    public Bitmap compressBitmap(Object... params) {

        if(!(params[0] instanceof Bitmap)){
            throw new ClassCastException("First parameter need input Bitmap object");
        }

        if(!(params[1] instanceof Integer)){
            throw new ClassCastException("Second parameter need input Integer object");
        }

        if(!(params[2] instanceof CompressStandard)){
            throw new ClassCastException("Thirdly parameter need input Enum object");
        }
        //压缩对象
        Bitmap compressObject= (Bitmap) params[0];
        //压缩到多少
        int toLen= (int) params[1];
        //压缩标准边
        int standard= (int) params[2];
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=false;
        int width=options.outWidth;
        int height=options.outHeight;

        int toWidth=0;
        int toHeight=0;
        //水平长度为标准时 计算压缩比例
       if (standard==CompressStandard.HORIZON.getValues()){
           int multiple=width/toLen;
           toWidth=width;
           toHeight=height/multiple;
       }
        //垂直长度为标准时 计算压缩比例
       if(standard==CompressStandard.VERTICAL.getValues()){
           int multiple=height/toLen;
           toHeight=height;
           toWidth=width/multiple;
       }

       if(toWidth==0 || toHeight==0){
           throw new NullPointerException("Image compress failed because toWidth==0 or toHeight==0");
       }
       Bitmap compressBtp=Bitmap.createScaledBitmap(compressObject,toWidth,toHeight,true);

        return compressBtp;
    }
}
