package com.july.teacup.ImageUtils.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 *      质量压缩
 *      在压缩后 图片的大小是没有改变的
 *      因为质量压缩不会减少图片的像素，它是在保持像素的前提下
 *      改变图片的位深和透明度，来达到压缩图片的目的，这也是为什么
 *      该方法叫质量压缩
 *
 *      如果用compress（CompressFormat.PNG,quality,byteArrayOutputStream）压缩png格式，
 *      quality就没有作用，bytes.length不会改变，因为png图片是无损的不能进行压缩
 *
 *      multiple 倍率
 *      quality  质量
 */
public class QualityCompress extends CompressBasics implements ImageCompressInterface {


    /**
     * @param params
     *      第一个参数为需要压缩的Bitmap对象 Bitmap
     *      第二个参数为图片质量压缩的倍率 int
     * @return
     */
    @Override
    public Bitmap compressBitmap(Object... params) {
        if(!(params[0] instanceof Bitmap)){
            throw new ClassCastException("First parameter need input Bitmap object");
        }

        if(!(params[1] instanceof Integer)){
            throw new ClassCastException("Second parameter need input Integer object");
        }

        Bitmap compressObject= (Bitmap) params[0];

        int quality= (int) params[1];

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        compressObject.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        Bitmap compressBtp=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return compressBtp;
    }
}
