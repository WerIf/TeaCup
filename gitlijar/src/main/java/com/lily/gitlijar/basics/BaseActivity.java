package com.lily.gitlijar.basics;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.lily.gitlijar.annotation.autoknife.FindKnifeProcess;
import com.lily.gitlijar.annotation.autowired.AutoWriedProcess;
import com.lily.gitlijar.bean.BasicsBean;
import com.lily.gitlijar.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class BaseActivity extends AppCompatActivity {

    private Gson gson;
    private JSONObject object;

    public abstract void beforeOnCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        beforeOnCreate();
        this.setContentView(this.getContentViewResId());
        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);
        init(savedInstanceState);
    }

    public abstract int getContentViewResId();

    public abstract void init(Bundle savedInstanceState);

    protected <T extends BasicsBean> T json(String jsonResult, Class<T> cltType) {
        if (gson == null) gson = new Gson();

        if (TextUtils.isEmpty(jsonResult)) {

            ToastUtils.makeText(this, "数据获取为空", ToastUtils.LENGTH_LONG).show();

            return null;
        }

        T clt = gson.fromJson(jsonResult, cltType);

        return clt;
    }

    protected String jsonCondition(String jsonResult, String jsonKey) {
        try {
            if (object == null) object = new JSONObject(jsonResult);

            return object.getString(jsonKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void start(Intent intent, String className) {
        try {
            Class clz = Class.forName(className);
            intent.setClass(this, clz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {

        }
    }

    protected void start(Intent intent, String className, int requestCode) {
        try {
            Class clz = Class.forName(className);
            intent.setClass(this, clz);
            startActivityForResult(intent, requestCode);
        } catch (ClassNotFoundException e) {

        }
    }


}
