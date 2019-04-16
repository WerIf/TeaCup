package com.teacup.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;


import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.bean.BaseBean;
import com.teacup.R;

public class Tab3 extends BaseFragment {

    public static String INTERFACE = Tab3.class.getName();



    @Override
    public int getLayoutResId() {
        return R.layout.layout_tab3;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onBridge(BaseActivity baseActivity) {
        super.onBridge(baseActivity);

        FragmentManager fm=baseActivity.getSupportFragmentManager();

        Tab3 targetFragment= (Tab3) fm.findFragmentByTag(getTag());

        baseActivity.setFunctionForFragment(INTERFACE,targetFragment);
    }


    @Override
    public <T extends BaseBean> void onDataChangeListener(T t) {

    }
}