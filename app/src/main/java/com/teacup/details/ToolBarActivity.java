package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        stop.setOnClickListener(v -> radarView.stop());

        launch.setOnClickListener(v -> radarView.start());
    }

    @Override
    public void beforeOnCreate() {

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
}
