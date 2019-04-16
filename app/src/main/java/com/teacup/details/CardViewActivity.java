package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.view.WaveView;
import com.teacup.R;


/**
 *  编写资料地址:https://www.jianshu.com/p/e429bb41bdb6
 */
public class CardViewActivity extends BaseActivity {

    @FindView(R.id.waveView)
    WaveView waveView;


    public static void start(Context context){
        context.startActivity(new Intent(context,CardViewActivity.class));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_card_view;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }

    @Override
    public void beforeOnCreate() {

    }

    public void start(View view) {

        waveView.start();
    }
}
