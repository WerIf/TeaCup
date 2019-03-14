package com.lily.gitlijar.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.lily.gitlijar.annotation.autoknife.FindKnifeProcess;
import com.lily.gitlijar.annotation.autowired.AutoWriedProcess;

import sl.nim.netease.com.gitlijar2.R;

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
