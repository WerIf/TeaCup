package com.lily.gitlijar.dialog;

import android.content.Context;

import com.lily.gitlijar.dialog.details.HintDialog;

public class GitLiDialog<T> {
    public static <T extends  BaseDialog> T getService(Class<T> tClass, Context context) {

        BaseDialog excessive = backObject(tClass, context);

        if(excessive!=null)return (T)excessive;
        else throw new NullPointerException("Back null pointer object");

    }

    private static <T extends  BaseDialog> T backObject(Class<T> tClass, Context context) {

        if(tClass == HintDialog.class){
            return (T)(new HintDialog(context));
        }


        return null;
    }
}
