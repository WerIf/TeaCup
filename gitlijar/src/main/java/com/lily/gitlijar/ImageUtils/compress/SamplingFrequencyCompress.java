package com.lily.gitlijar.ImageUtils.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *      采样率压缩
 *      通过设置multiple的值(int)后，加入设置为2，则宽和高都为原来的1/2，宽高都减少了，
 *      内存自然就降低了
 *
 *      Sampling Frequency  采样率
 */
public class SamplingFrequencyCompress extends CompressBasics implements ImageCompressInterface {


    /**
     * @param params
     *      第一个参数为图片加载路径    String
     *      第二位为图片压缩到最小边为多少dp   int
     * @return
     */
    @Override
    public Bitmap compressBitmap(Object... params) {
        if(!(params[0] instanceof String)){
            throw new ClassCastException("First parameter need input String object");
        }

        if(!(params[1] instanceof Integer)){
            throw new ClassCastException("Second parameter need input Integer object");
        }

        //图片保存路径
        String savePath= (String) params[0];
        int compressToDp= (int) params[1];
        BitmapFactory.Options options=new BitmapFactory.Options();
        //只获取图片的大小，不将图片加载进内存，防止出现OOM
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(savePath,options);
        //图片默认压缩比例
        int inSampleSize= 2;
        int width=options.outWidth;
        int height=options.outHeight;
        int minLen=Math.min(width,height);  //获取原图的最小边长
        if(minLen>compressToDp){ //如果原始图像的最小边长大于100dp(此处单位是dp，而非px)
            float ratio=minLen/((float)compressToDp);//计算图片像素压缩比例
            inSampleSize= (int) ratio;
        }
        //计算好压缩比例，则此可以加载原图
        options.inJustDecodeBounds=false;
        //设置为刚才计算好的压缩比例
        options.inSampleSize=inSampleSize;
        //解码文件
        Bitmap compressBtp=BitmapFactory.decodeFile(savePath,options);

        return compressBtp;
    }
}
