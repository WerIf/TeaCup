package com.teacup.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.bean.BaseBean;
import com.july.teacup.toast.ToastUtils;
import com.july.teacup.view.TestView;
import com.teacup.R;
import com.teacup.bean.MessageEvent;

public class Tab2 extends BaseFragment {

    public static String INTERFACE = Tab2.class.getName();

    @FindView(R.id.tabClick)
    TextView view;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_tab2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tabClick)
    public void onClick(View view) {
        if (bridgeManager != null) {
            bridgeManager.invoke(INTERFACE);
        } else {
            ToastUtils.makeText(getContext(), "bridgeManager is null").show();
        }
    }


    @Override
    public void onBridge(BaseActivity baseActivity) {
        super.onBridge(baseActivity);

        FragmentManager fm = baseActivity.getSupportFragmentManager();

        Tab2 targetFragment = (Tab2) fm.findFragmentByTag(getTag());

        baseActivity.setFunctionForFragment(INTERFACE, targetFragment);
    }


    @Override
    public <T extends BaseBean> void onDataChangeListener(T t) {

        if (t == null) return;
        if (t instanceof MessageEvent) {
            view.setText(((MessageEvent) t).getName());
        }

    }
}
