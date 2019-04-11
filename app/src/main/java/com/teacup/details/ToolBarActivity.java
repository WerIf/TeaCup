package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.view.RadarView;
import com.teacup.R;


public class ToolBarActivity extends BaseActivity implements View.OnClickListener {

    @FindView(R.id.mToolBar)
    Toolbar toolbar;

    @FindView(R.id.radarView)
    RadarView radarView;

    @FindView(R.id.stop)
    Button stop;

    @FindView(R.id.start_Launch)
    Button launch;


    public static void start(Context context){
        context.startActivity(new Intent(context,ToolBarActivity.class));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tool_bar;
    }

    @Override
    public void init(Bundle savedInstanceState) {

//        toolbar.setLogo(R.mipmap.icon);
//        toolbar.setTitle(R.string.title);
//        toolbar.setSubtitle(R.string.subtitle);
//
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.back);



        toolbar.setNavigationOnClickListener(this);

//        stop.setOnClickListener(v -> radarView.stop());
//
//        launch.setOnClickListener(v -> radarView.start());
    }

    @Override
    public void beforeOnCreate() {
//        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
//        Transition fade = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
//        Transition move = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
//        Transition slide_left = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);
//
//        //使用动画时机
//        getWindow().setExitTransition(slide_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    public void translate(View view) {
        //两个界面拥有相同的图片 采用这种方式跳转
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.trans));
        ActivityCompat.startActivity(this, new Intent(this, MaterialDesignTestActivity.class), compat.toBundle());


        //从右下方弹出
//        ActivityOptionsCompat options4 = ActivityOptionsCompat.makeClipRevealAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 1000, 1000);
//        ActivityCompat.startActivity(this, new Intent(this, MaterialDesignTestActivity.class), options4.toBundle());

//        ActivityOptionsCompat options6 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//        ActivityCompat.startActivity(this, new Intent(this, MaterialDesignTestActivity.class), options6.toBundle());
    }
}
