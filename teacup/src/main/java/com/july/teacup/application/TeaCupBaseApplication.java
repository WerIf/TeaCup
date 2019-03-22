package com.july.teacup.application;

import android.app.Application;

import com.july.teacup.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TeaCupBaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/thin_golld.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}
