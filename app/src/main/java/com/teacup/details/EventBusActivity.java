package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.fragment_bridge.BridgeNoParamNoResult;
import com.july.teacup.fragment_bridge.BridgeWithParamOnly;
import com.july.teacup.fragment_bridge.BridgeWithParamWithResult;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;
import com.teacup.bean.MessageEvent;
import com.teacup.fragment.Tab1;
import com.teacup.fragment.Tab2;
import com.teacup.fragment.Tab3;

public class EventBusActivity extends BaseActivity {

    @FindView(R.id.frameLayout)
    FrameLayout frameLayout;


    private Tab2 tab2;
    private Tab3 tab3;
    private Tab1 tab1;

    public static void start(Context context) {
        context.startActivity(new Intent(context, EventBusActivity.class));
    }

    @Override
    public void beforeOnCreate() {

    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_event_bus;
    }

    @Override
    public void init(Bundle savedInstanceState) {



        initFragment();


    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        bridgeManager.addBridge(new BridgeNoParamNoResult(Tab1.INTERFACE) {
            @Override
            public void bridge() {

                Log.e("TAG","println result tag 4");

                ToastUtils.makeText(EventBusActivity.this, "调用了无参数接口", ToastUtils.LENGTH_LONG).show();

                tab2.onDataChangeListener(new MessageEvent("Teacup","23"));
            }
        }).addBridge(new BridgeNoParamNoResult(Tab2.INTERFACE) {
            @Override
            public void bridge() {

                ToastUtils.makeText(EventBusActivity.this, "调用了youfafa", ToastUtils.LENGTH_LONG).show();
            }
        }).addBridge(new BridgeWithParamWithResult<MessageEvent,String>(Tab3.INTERFACE) {
            @Override
            public String bridge(MessageEvent o) {
                return null;
            }
        }).addBridge(new BridgeWithParamOnly<String>(Tab2.INTERFACE) {
            @Override
            public void bridge(String String) {

            }
        });
        return bridgeManager;
    }


    private void initFragment() {

        Show1(1);

    }

    @OnClick({R.id.button, R.id.button1, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Show1(1);
                break;
            case R.id.button1:
                Show1(2);
                break;
            case R.id.button2:
                Show1(3);
                break;
        }
    }

    private void Show1(int index) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transcation = manager.beginTransaction();

        ToastUtils.makeText(this, "click :" + index).show();

        if (tab1 == null) {
            tab1 = new Tab1();
            transcation.add(R.id.frameLayout, tab1, Tab1.INTERFACE);
        }

        if (tab2 == null) {
            tab2 = new Tab2();
            transcation.add(R.id.frameLayout, tab2, Tab2.INTERFACE);
        }

        if (tab3 == null) {
            tab3 = new Tab3();
            transcation.add(R.id.frameLayout, tab3, Tab3.INTERFACE);
        }

        Hide(transcation);

        switch (index) {
            case 1:
                    transcation.show(tab1);
                break;
            case 2:
                    transcation.show(tab2);
                break;
            case 3:
                    transcation.show(tab3);
                break;
        }

        transcation.commit();


    }


    private void Hide(FragmentTransaction transcation) {

        if (tab1 != null && !tab1.isHidden()) {
            transcation.hide(tab1);
        }

        if (tab2 != null && !tab2.isHidden()) {
            transcation.hide(tab2);
        }

        if (tab3 != null && !tab3.isHidden()) {
            transcation.hide(tab3);
        }


    }
}
