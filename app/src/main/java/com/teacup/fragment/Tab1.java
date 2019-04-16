package com.teacup.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.bean.BaseBean;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;
import com.teacup.fragment.contract.DataContract;
import com.teacup.fragment.presenter.DataPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tab1 extends BaseFragment implements DataContract.View {

    public static String INTERFACE = Tab1.class.getName();

    TextView textView;

    @Override
    public int getLayoutResId() {
        return R.layout.tab1;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        DataContract.Presenter presenter=new DataPresenter(this);
        ((DataPresenter) presenter).start("",new HashMap<>());
    }

    @OnClick(R.id.click)
    public void onClick(View view){

        Log.e("TAG","println result tag 1");

        if(bridgeManager!=null){
            bridgeManager.invoke(INTERFACE);
        }else{
            ToastUtils.makeText(getContext(),"bridgeManager is null").show();
        }
    }


    @Override
    public void onBridge(BaseActivity baseActivity) {
        super.onBridge(baseActivity);

        FragmentManager fm=baseActivity.getSupportFragmentManager();

        Tab1 targetFragment= (Tab1) fm.findFragmentByTag(getTag());

        baseActivity.setFunctionForFragment(INTERFACE,targetFragment);
    }

    @Override
    public <T extends BaseBean> void onDataChangeListener(T t) {

    }

    @Override
    public void success() {

    }

    @Override
    public void failed() {

    }
}
