package com.july.teacup.dialog.details;

import android.content.Context;
import android.os.Bundle;

import com.july.teacup.R;
import com.july.teacup.dialog.BaseDialog;


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
