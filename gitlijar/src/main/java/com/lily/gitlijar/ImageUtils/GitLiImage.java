package com.lily.gitlijar.ImageUtils;

import com.lily.gitlijar.ImageUtils.compress.CompressBasics;
import com.lily.gitlijar.ImageUtils.compress.ImageCompressInterface;

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
