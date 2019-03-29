package com.july.teacup.dialog.details;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.july.teacup.R;
import com.july.teacup.dialog.BaseDialog;


public class InformDialog extends BaseDialog {

    private TextView inform_title;
    private TextView inform_data;
    private TextView inform_sure;

    private String mTitle;
    private String mData;
    private String mSure;

    private GitLiClickAffirm onClickAffirm;

    public void setOnClickAffirm(String affirmData,GitLiClickAffirm onClickAffirm) {
        if(!TextUtils.isEmpty(affirmData)){
            mSure=affirmData;
        }
        this.onClickAffirm = onClickAffirm;
    }

    public InformDialog(Context context) {
        super(context);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.inform_dialog_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        inform_title=findViewById(R.id.inform_title);
        inform_data=findViewById(R.id.inform_data);
        inform_sure=findViewById(R.id.inform_sure);
    }

    private void initData() {
       inform_title.setText(mTitle);
       inform_data.setText(mData);
       inform_sure.setText(mSure);
    }

    private void initEvent() {
        inform_sure.setOnClickListener(v -> {
            onClickAffirm.onGitLiClickAffirm(v);
            this.dismiss();
        });
    }

    public InformDialog setTitle(String ifTitle){
        if(!TextUtils.isEmpty(ifTitle)){
            mTitle=ifTitle;
        }

        return this;
    }

    public InformDialog setData(String ifData){
        if(!TextUtils.isEmpty(ifData)){
            mData=ifData;
        }
        return this;
    }

}
