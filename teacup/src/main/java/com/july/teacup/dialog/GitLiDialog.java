package com.july.teacup.dialog;

import android.content.Context;

import com.july.teacup.dialog.details.HintDialog;

public class GitLiDialog<T> {

    private static BaseDialog baseDialog = null;

    public static <T extends  BaseDialog> T getService(Class<T> tClass, Context context) {

        BaseDialog excessive = backObject(tClass, context);

        if(excessive!=null)return (T)excessive;
        else throw new NullPointerException("Back null pointer object");

    }

    private static <T extends  BaseDialog> T backObject(Class<T> tClass, Context context) {
        
            synchronized (tClass){
                if(baseDialog==null){
                    try {
                        baseDialog=tClass.newInstance();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }

            return (T)baseDialog;

    }
}
