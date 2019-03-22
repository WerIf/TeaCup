package com.july.teacup.dialog.details;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.july.teacup.R;
import com.july.teacup.dialog.BaseDialog;


public class HintDialog extends BaseDialog {

    private TextView hintTitle;
    private TextView hintData;
    private TextView hintCancel;
    private TextView hintSure;

    private String mTitle;
    private String mData;
    private String mCancel;
    private String mSure;

    private GitLiClickAffirm onClickAffirm;
    private GitLiClickDisqualify onClickDisqualify;

    public HintDialog(Context context) {
        super(context);
    }

    public HintDialog setOnClickAffirm(String affirmData,GitLiClickAffirm onClickAffirm) {
        if(!TextUtils.isEmpty(affirmData)){
            mSure=affirmData;
        }
        this.onClickAffirm = onClickAffirm;

        return this;
    }

    public HintDialog setOnClickDisqualify(String disqualifyData,GitLiClickDisqualify onClickDisqualify) {
        if (!TextUtils.isEmpty(disqualifyData)){
            mCancel=disqualifyData;
        }
        this.onClickDisqualify = onClickDisqualify;

        return this;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.hint_dialog_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        initView();

        initData();

        initEvent();
    }

    private void initView() {
        hintTitle=findViewById(R.id.hint_title);
        hintData=findViewById(R.id.hint_data);
        hintCancel=findViewById(R.id.hint_cancel);
        hintSure=findViewById(R.id.hint_sure);
    }

    private void initData() {

        hintTitle.setText(mTitle);
        hintData.setText(mData);
        hintCancel.setText(mCancel);
        hintSure.setText(mSure);
    }

    private void initEvent(){

        hintSure.setOnClickListener(view -> {onClickAffirm.onGitLiClickAffirm(hintSure);this.dismiss();});
        hintCancel.setOnClickListener(view -> {onClickDisqualify.onGitLiClickDisqualify(hintCancel);this.dismiss();});
    }

    public HintDialog setTitle(String rpTitle){
        if(!TextUtils.isEmpty(rpTitle)){
            mTitle=rpTitle;
        }
        return this;
    }

    public HintDialog setData(String rpData){
        if(!TextUtils.isEmpty(rpData)){
            mData=rpData;
        }
        return this;
    }

}
