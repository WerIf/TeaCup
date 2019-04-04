package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.july.teacup.ImageUtils.TeaCupImage;
import com.july.teacup.ImageUtils.load.ImageLoadUtils;
import com.july.teacup.adaptive.NavigationBar;
import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.teacup.R;


public class CoordLayoutActivity extends BaseActivity {

    @FindView(R.id.toolbar)
    Toolbar toolbar;

    @FindView(R.id.backdrop)
    ImageView backdrop;
    //https://graph.baidu.com/resource/102fda281f5ea56e567b701553845595.jpg

    public static void start(Context context) {
        context.startActivity(new Intent(context, CoordLayoutActivity.class));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_coord_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbar.setTitle("this is title");
        NavigationBar.handle(this);
        setSupportActionBar(toolbar);
        //显示返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        ImageLoadUtils.getInstance().ImageLoad(this, backdrop, "https://graph.baidu.com/resource/102fda281f5ea56e567b701553845595.jpg");
//        TeaCupImage.getService(ImageLoadUtils.class).ImageLoad(this, backdrop, "https://graph.baidu.com/resource/102fda281f5ea56e567b701553845595.jpg");

        Log.e("TAG","println result :"+(TeaCupImage.getService(ImageLoadUtils.class) ==null));
    }

    @Override
    public void beforeOnCreate() {
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        if (Build.VERSION.SDK_INT >= 21){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }


}
