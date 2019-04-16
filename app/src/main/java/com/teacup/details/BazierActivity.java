package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.teacup.R;
import com.teacup.fragment.Tab1;
import com.teacup.fragment.Tab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BazierActivity extends BaseActivity
{

    private List<String> titles = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6", "Tab7", "Tab8", "Tab9");

    public static void start(Context context){
        context.startActivity(new Intent(context,BazierActivity.class));
    }


    @FindView(R.id.mTableLayout)
    TabLayout mTableLayout;

    @FindView(R.id.mViewPager)
    ViewPager mViewPager;

    @FindView(R.id.mToolbar)
    Toolbar mToolbar;

    private List<Fragment> fragmentList=new ArrayList();

    @Override
    public void beforeOnCreate() {

    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_bazier;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true);

        fragmentList.add(new Tab1());
        fragmentList.add(new Tab2());
        fragmentList.add(new Tab1());
        fragmentList.add(new Tab2());
        fragmentList.add(new Tab1());
        fragmentList.add(new Tab2());
        fragmentList.add(new Tab1());
        fragmentList.add(new Tab2());
        fragmentList.add(new Tab1());

        mViewPager.setAdapter(new TcPageAdapter(getSupportFragmentManager()));

        mTableLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }

    public void onClick(View view){
        Log.d("TAG", "onClick execute");
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","println log is:"+event.getAction());
        return false;
    }

    class TcPageAdapter extends FragmentPagerAdapter{


        public TcPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
