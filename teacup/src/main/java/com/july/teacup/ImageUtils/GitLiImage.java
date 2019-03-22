package com.july.teacup.ImageUtils;

import com.july.teacup.ImageUtils.compress.CompressBasics;

public class GitLiImage<T> {
    public static <T extends CompressBasics> T getService(Class<T> tClass){
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
