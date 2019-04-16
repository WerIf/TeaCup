package com.teacup.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.click.EventListener;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;

public class MaterialDesignTestActivity extends BaseActivity {

    @FindView(R.id.head)
    private ImageView head;

    @Override
    public void beforeOnCreate() {

    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_material_disign_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        head.setOnClickListener(new EventListener(this));
    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }

    @OnClick({R.id.head})
    public void onClick(View view){
        ToastUtils.makeText(this,"hello",ToastUtils.LENGTH_LONG).show();
    }
}
