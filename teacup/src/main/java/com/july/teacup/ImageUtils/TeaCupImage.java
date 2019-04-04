package com.july.teacup.ImageUtils;

import com.july.teacup.ImageUtils.imgbasics.ImageBasice;

public class TeaCupImage<T> {
    public static <T extends ImageBasice> T getService(Class<T> tClass){
        try {
            return (T)tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
