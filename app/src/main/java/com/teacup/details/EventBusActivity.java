package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.fragment_bridge.BaseBridge;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.fragment_bridge.BridgeNoParamNoResult;
import com.july.teacup.fragment_bridge.BridgeWithParamWithResult;
import com.july.teacup.fragment_bridge.BridgeWithResultOnly;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;
import com.teacup.fragment.Tab1;
import com.teacup.fragment.Tab2;
import com.teacup.fragment.Tab3;

public class EventBusActivity extends BaseActivity {

    @FindView(R.id.frameLayout)
    FrameLayout frameLayout;

    public static void start(Context context){
        context.startActivity(new Intent(context,EventBusActivity.class));
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


        FragmentManager f = getSupportFragmentManager();
        FragmentTransaction transcation = f.beginTransaction();

        initFragment(transcation,f);


    }

    private void initFragment(FragmentTransaction transcation,FragmentManager f) {
        Tab1 tab1= (Tab1) f.findFragmentByTag(Tab1.INTERFACE);
        if (tab1==null){
            tab1 = new Tab1();
            //重新添加fragment
            transcation.add(R.id.frameLayout,tab1, Tab1.INTERFACE);
            transcation.addToBackStack(Tab1.INTERFACE);


        }

        Tab2 tab2= (Tab2) f.findFragmentByTag(Tab2.INTERFACE);
        if(tab2==null){
            tab2=new Tab2();
            transcation.add(R.id.frameLayout,tab2, Tab2.INTERFACE);
        }

        transcation.commit();
    }

    public void setFunctionForFragment(String tag){
        FragmentManager fm=getSupportFragmentManager();

        Tab1 baseBridge= (Tab1) fm.findFragmentByTag(tag);

        Log.e("TAG","judge Tab1:"+baseBridge);

        BridgeManager bridgeManager=BridgeManager.getInstance();

        baseBridge.setBridgeManager(bridgeManager.addBridge(new BridgeNoParamNoResult(Tab1.INTERFACE) {
            @Override
            public void bridge() {

                ToastUtils.makeText(EventBusActivity.this,"调用了无参数接口",ToastUtils.LENGTH_LONG).show();
            }
        }).addBridge(new BridgeWithResultOnly<String>(Tab1.INTERFACE) {
            @Override
            public String bridge() {
                return null;
            }
        }).addBridge(new BridgeWithParamWithResult<String,String>(Tab1.INTERFACE) {
            @Override
            public String bridge(String o) {
                return null;
            }
        }));
    }
}
