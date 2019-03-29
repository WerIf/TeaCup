package com.teacup;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.july.teacup.application.TeaCupBaseApplication;

import com.teacup.db.DaoMaster;
import com.teacup.db.DaoSession;

import java.io.File;


public class BaseApplication extends TeaCupBaseApplication {

    private final String TAG = "BaseApplication";

    private DaoSession daoSeesion;

    private static final String APKPATCH_PATH="/out.apatch";

    @Override
    public void onCreate() {
        super.onCreate();

        initDatabase();

    }



    private void initDatabase() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"oneTest.db");
        //获取可写数据库
        SQLiteDatabase db=helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster=new DaoMaster(db);
        //获取Dao对象管理者
        daoSeesion=daoMaster.newSession();
    }

    public DaoSession getDaoInstant(){
        return daoSeesion;
    }



}
