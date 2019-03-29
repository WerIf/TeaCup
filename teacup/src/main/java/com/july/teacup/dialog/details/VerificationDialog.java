package com.july.teacup.dialog.details;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.july.teacup.R;
import com.july.teacup.dialog.BaseDialog;
import com.july.teacup.view.TeaEditText;

public class VerificationDialog extends BaseDialog {

    private TextView vrfTitle;
    private Button vrfVrf;
    private TeaEditText vrfInput;
    private Button vrfSure;

    private String mTitle;
    private String mVrf;
    private String mSure;


    //总共时长
    private long mTimeLength=60000;
    //步长
    private long mTimeStep=1000;

    //确定监听
    private GitLiClickAffirm onClickAffirm;
    //验证码时间监听
    private GitLiVerificationBack onVerification;

    public void setOnVerification(GitLiVerificationBack onVerification) {
        this.onVerification = onVerification;
    }

    public void setOnClickAffirm(String affirmData, GitLiClickAffirm onClickAffirm) {
        if(!TextUtils.isEmpty(affirmData)){
            mSure=affirmData;
        }
        this.onClickAffirm = onClickAffirm;
    }

    public VerificationDialog(Context context) {
        super(context);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.verification_dialog_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

       initView();

       initData();

       initEvent();
    }



    private void initView() {
        vrfTitle=findViewById(R.id.verification_title);
        vrfInput=findViewById(R.id.verification_input);
        vrfVrf=findViewById(R.id.verification_vrf);
        vrfSure=findViewById(R.id.verification_sure);
    }

    private void initData() {
        vrfSure.setText(mSure);
        vrfTitle.setText(mTitle);
        vrfVrf.setText(mVrf);
    }

    public VerificationDialog setTitle(String title){
        if(!TextUtils.isEmpty(title)){
            mTitle=title;
        }
        return this;
    }

    public VerificationDialog setVrf(String vrf,long timeLength,long timeStep){
        if(!TextUtils.isEmpty(vrf)){
            mVrf=vrf;
        }
        this.mTimeLength=timeLength;
        this.mTimeStep=timeStep;
        return this;
    }

    private void initEvent() {

        vrfSure.setOnClickListener(v -> {
            onClickAffirm.onGitLiClickAffirm(v);
            this.dismiss();
        });

        vrfVrf.setOnClickListener(v -> start(mTimeLength,mTimeStep));
    }

    /**
     * @param longTime  总共时长
     * @param step      步长
     */
    private void start(long longTime,long step){
        CountDownTimer downTimer=new CountDownTimer( longTime,step) {
            @Override
            public void onTick(long millisUntilFinished) {
//                String time=String.valueOf((int) millisUntilFinished/1000);
                onVerification.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                onVerification.onFinish();
            }
        };

        downTimer.start();
    }
}
