package com.lily.gitlijar.dialog.details;

import android.content.Context;
import android.os.Bundle;

import com.lily.gitlijar.dialog.BaseDialog;

import sl.nim.netease.com.gitlijar2.R;

public class InformDialog extends BaseDialog {

    public InformDialog(Context context) {
        super(context);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.inform_dialog_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }
}
