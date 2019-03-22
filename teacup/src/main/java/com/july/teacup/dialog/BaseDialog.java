package com.july.teacup.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.july.teacup.R;


public abstract class BaseDialog extends Dialog {

    private final Context mContent;
    public BaseDialog(Context context) {
        super(context,R.style.ShowDialog);
        mContent=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        init(savedInstanceState);
    }

    public abstract int getContentViewResId();

    public abstract void init(Bundle savedInstanceState);
}
