package com.lily.gitlijar.ImageUtils.compress;

import android.graphics.Bitmap;

/**
 *      android中图片是以bitmap形式存在的
 *      计算bitmap所占内存大小计算方式：
 *          图片长度 * 图片宽度 * 一个像素点占用的字节数
 *      以下是图片的压缩格式：
 *          其中 A代表透明度 R代表红色 G代表绿色 B代表蓝色
 *              ALPHA_8
 *                  表示8位Alpha位图，即A=8，一个像素点占用1个字节，它没有颜色，只有透明度
 *              ARGB_4444
 *                  表示16位ARGB位图，即A=4，R=4，G=4，B=4，一个像素点4+4+4+4=16位，2个字节
 *              ARGB_8888
 *                  表示32位ARGB位图，即A=8，R=8，G=8，B=8，一个像素点8+8+8+8=32位，4个字节
 *              RGB_565
 *                  表示16位RGB位图，即R=5，G=6，B=6,它没有透明度，一个像素点占5+6+5=16位，2个字节
 */
public  interface ImageCompressInterface {
    Bitmap compressBitmap(Object ... params);
}
