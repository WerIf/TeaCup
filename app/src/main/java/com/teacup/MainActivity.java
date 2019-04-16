package com.teacup;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.july.teacup.basics.NetWorkCondition;
import com.july.teacup.permission.PermissionHelper;
import com.july.teacup.permission.PermissionInterface;
import com.july.teacup.recycler.GitliRecyclerAdapter;
import com.july.teacup.recycler.GitliViewHolder;
import com.july.teacup.recycler.OnBackViewHolder;
import com.july.teacup.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;


import com.teacup.bean.MessageEvent;
import com.teacup.details.BazierActivity;
import com.teacup.details.CardViewActivity;
import com.teacup.details.CoordLayoutActivity;
import com.teacup.details.EventBusActivity;
import com.teacup.details.SqliteActivity;
import com.teacup.details.TestBezierActivity;
import com.teacup.details.ToolBarActivity;



public class MainActivity extends Activity implements NetWorkCondition, PermissionInterface {


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String string = (String) msg.obj;
            ToastUtils.makeText(MainActivity.this, string, ToastUtils.LENGTH_SHORT).show();

            Log.v("MainActivity", "get println result is:" + string);

        }
    };

    private String[] lists=new String[]{
            "toolbar",
            "cardView",
            "coordinator",
            "greendao",
            "bezier",
            "bezier 测试",
            "fragment 测试"
    };

    RecyclerView recyclerView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_main);


        init();
    }




    public void init() {

        recyclerView=findViewById(R.id.recyclerView);

        EventBus.getDefault().register(this);


        PermissionHelper helper = new PermissionHelper(this, this);
        helper.requestPermissions();

        initView();
    }

    private void initView() {
//        evenBus.setOnClickListener(view -> EventBus.getDefault().post(new MessageEvent("gitly", "15")));

        GitliRecyclerAdapter adapter=new GitliRecyclerAdapter(this,R.layout.item,lists.length);

        adapter.setOnBackViewHolder(new OnBackViewHolder() {
            @Override
            public void backViewHolder(GitliViewHolder mViewHolder, int position) {
                mViewHolder.getViewFromId(R.id.item_data,TextView.class).setText(lists[position]);
            }

            @Override
            public void clickViewHolder(View currentView,int currentPosition) {

                switch (currentPosition){
                    case 0:
                        ToolBarActivity.start(MainActivity.this);
                        break;
                    case 1:
                        CardViewActivity.start(MainActivity.this);
                        break;
                    case 2:
                        CoordLayoutActivity.start(MainActivity.this);
                        break;
                    case 3:
                        SqliteActivity.start(MainActivity.this);
                        break;
                    case 4:
                        BazierActivity.start(MainActivity.this);
                        break;
                    case 5:
                        TestBezierActivity.start(MainActivity.this);
                        break;
                    case 6:
                        EventBusActivity.start(MainActivity.this);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

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
//        loading_one.setImageBitmap(bitmap);
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


    public void Crash(View view) {
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
