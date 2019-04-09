package com.july.teacup.basics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.july.teacup.annotation.autoknife.FindKnifeProcess;
import com.july.teacup.annotation.autowired.AutoWriedProcess;
import com.july.teacup.bean.BaseBean;
import com.july.teacup.click.EventListener;
import com.july.teacup.toast.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity extends AppCompatActivity {

    private Gson gson;
    private JSONObject object;
    private Toolbar toolbar;

    public abstract void beforeOnCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        beforeOnCreate();
        this.setContentView(this.getContentViewResId());
        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);
        init(savedInstanceState);
    }

    public abstract int getContentViewResId();

    protected void setToolbar(int toolbarId, int titleId, int logoId) {
        toolbar = findViewById(toolbarId);
        toolbar.setLogo(logoId);
        toolbar.setTitle(titleId);
        setSupportActionBar(toolbar);
    }

    protected void setToolbar(int toolbarId, ToolBarOptions options) {
        toolbar = findViewById(toolbarId);
        if (options.titleId != 0) {
            toolbar.setTitle(options.titleId);
        }

        if (!TextUtils.isEmpty(options.titleString)) {
            toolbar.setTitle(options.titleString);
        }

        if (options.logoId != 0) {
            toolbar.setLogo(options.logoId);
        }

        if (options.isNeedNavigate) {
            toolbar.setNavigationIcon(options.navigateId);
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setNavigationOnClickListener(v ->
                    onNavigateUpClick());
        }
    }

    private void onNavigateUpClick() {
        onBackPressed();
    }

    /**
     * 页面初始化
     *
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);

    /**
     * 解析数据
     *
     * @param jsonResult
     * @param cltType
     * @param <T>
     * @return
     */
    protected <T extends BaseBean> T json(String jsonResult, Class<T> cltType) {
        if (gson == null) gson = new Gson();

        if (TextUtils.isEmpty(jsonResult)) {

            ToastUtils.makeText(this, "数据获取为空", ToastUtils.LENGTH_LONG).show();

            return null;
        }

        T clt = gson.fromJson(jsonResult, cltType);

        return clt;
    }

    /**
     * 解析网络请求状态值 例如：status message
     *
     * @param jsonResult
     * @param jsonKey
     * @return
     */
    protected String jsonCondition(String jsonResult, String jsonKey) {
        try {
            if (object == null) object = new JSONObject(jsonResult);

            return object.getString(jsonKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 页面无值跳转
     *
     * @param intent
     * @param className
     */
    protected void start(Intent intent, String className) {
        try {
            Class clz = Class.forName(className);
            intent.setClass(this, clz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {

        }
    }

    /**
     * 页面带值跳转
     *
     * @param intent
     * @param className
     * @param requestCode
     */
    protected void start(Intent intent, String className, int requestCode) {
        try {
            Class clz = Class.forName(className);
            intent.setClass(this, clz);
            startActivityForResult(intent, requestCode);
        } catch (ClassNotFoundException e) {

        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public <T extends View> T findClickView(int id) {

        T view = (T) findViewById(id);
        view.setOnClickListener(new EventListener(this));
        return view;
    }

}
