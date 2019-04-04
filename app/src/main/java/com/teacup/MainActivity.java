package com.teacup;

import android.Manifest;
import android.graphics.Bitmap;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.basics.NetWorkCondition;
import com.july.teacup.dialog.GitLiDialog;
import com.july.teacup.dialog.details.HintDialog;
import com.july.teacup.hotupdate.DexManager;
import com.july.teacup.permission.PermissionHelper;
import com.july.teacup.permission.PermissionInterface;
import com.july.teacup.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;


import com.taobao.sophix.SophixManager;
import com.teacup.been.MessageEvent;
import com.teacup.details.BazierActivity;
import com.teacup.details.CardViewActivity;
import com.teacup.details.CoordLayoutActivity;
import com.teacup.details.SheetActivity;
import com.teacup.details.SqliteActivity;
import com.teacup.details.TestBezierActivity;
import com.teacup.details.ToolBarActivity;


public class MainActivity extends BaseActivity implements NetWorkCondition, PermissionInterface {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String string = (String) msg.obj;
            ToastUtils.makeText(MainActivity.this, string, ToastUtils.LENGTH_SHORT).show();

            Log.v("MainActivity", "get println result is:" + string);

        }
    };

    @FindView(R.id.loading_one)
    ImageView loading_one;

    @FindView(R.id.loading_two)
    ImageView loading_two;

    @FindView(R.id.jdk_lambda)
    Button lambda;

    @FindView(R.id.send_evenBus)
    Button evenBus;

    @FindView(R.id.show_evenBus)
    TextView showText;

    @Override
    public void beforeOnCreate() {

    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);



        PermissionHelper helper=new PermissionHelper(this,this);
        helper.requestPermissions();



        lambda.setText("程序员发现并解决了bug");



        lambda.setOnClickListener(
                view ->
                        GitLiDialog.getService(HintDialog.class, this)
                                .setTitle("HINT TITLE")
                                .setData("hello world")
                                .setOnClickAffirm("确认", view1 -> ToastUtils.makeText(MainActivity.this, "click sure", ToastUtils.LENGTH_LONG).show())
                                .setOnClickDisqualify("取消", view1 -> ToastUtils.makeText(MainActivity.this, "click cancel", ToastUtils.LENGTH_LONG).show())
                                .show()
        );


        initView();
    }

    private void initView() {
        evenBus.setOnClickListener(view -> EventBus.getDefault().post(new MessageEvent("gitly", "15")));
    }



    @OnClick({R.id.toolBar, R.id.cardView, R.id.coordinator, R.id.sheet, R.id.network_urlconnection, R.id.network_httpurlconnection, R.id.show_evenBus, R.id.greeDao,R.id.bezier,R.id.testbezier})
    public void onClick(View view) {

        Map<String, String> params = initMap();

        switch (view.getId()) {
            case R.id.toolBar:
                ToolBarActivity.start(this);
                break;
            case R.id.cardView:
                CardViewActivity.start(this);
                break;
            case R.id.coordinator:
                CoordLayoutActivity.start(this);
                break;
            case R.id.sheet:
                SheetActivity.start(this);
                break;
            case R.id.network_urlconnection:

                Log.v("MainActivity", "get println result is:" + params.get("telephone") + "  password:" + params.get("password"));
                break;
            case R.id.network_httpurlconnection:

                Log.v("MainActivity", "get println result is:" + params.get("telephone") + "  password:" + params.get("password"));

                break;
            case R.id.greeDao:
                SqliteActivity.start(this);
                break;
            case R.id.bezier:
                BazierActivity.start(this);
                break;
            case R.id.testbezier:
                TestBezierActivity.start(this);
                break;

        }
    }

    private Map<String, String> initMap() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("telephone", "17743266993");
        paramsMap.put("password", "123456");
        return paramsMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent messageEvent) {
        ToastUtils.makeText(this, messageEvent.toString(), ToastUtils.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(Bitmap bitmap) {


        loading_one.setImageBitmap(bitmap);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(String string) {


        Log.e("TAG", "println result is:" + string);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSucceed(Object object) {
//        Log.e("TAG","println result is:"+(bitmap==null));
        Bitmap bitmap = (Bitmap) object;
        if (bitmap == null) return;
        EventBus.getDefault().post(bitmap);
    }

    @Override
    public void onFailed(Object object) {
        EventBus.getDefault().post("failed:" + (int) object);
    }

    @Override
    public void onError(Object object) {
        EventBus.getDefault().post("error:" + (String) object);
    }


    public void Crash(View view){
        // 当我们传进去null的时候，程序会抛出异常，崩溃
        Log.e("TAG", null);
        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
    }



    @Override
    public int getPermissionsRequestCode() {
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
    }

    @Override
    public void requestPermissionsSuccess() {

    }

    @Override
    public void requestPermissionsFail() {

    }
}
