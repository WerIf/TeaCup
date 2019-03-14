package com.lily.gitlijar.ImageUtils.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 *      RGB_565法
 *      图片大小直接压缩到原来的一半，但是长度和宽度没有变，相比ARGB_8888减少了一般的内存
 *      注意：由于ARGB_4444的画质惨不忍睹，一般假如对图片没有透明度要求的话，可以盖层RGB_565
 */
public class RGB_565Compress extends CompressBasics implements ImageCompressInterface {
    /**
     * @param params
     *      第一个参数为图片加载路径 String
     * @return
     */
    @Override
    public Bitmap compressBitmap(Object... params) {

        if(!(params[0] instanceof String)){
            throw new ClassCastException("First parameter need input String object");
        }

        String compressPath= (String) params[0];
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPreferredConfig=Bitmap.Config.RGB_565;
        Bitmap compressBtp=BitmapFactory.decodeFile(compressPath,options);
        return compressBtp;
    }
}
