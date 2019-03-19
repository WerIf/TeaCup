package sl.nim.netease.com.gitlijar;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import sl.nim.netease.com.gitlijar.db.DaoMaster;
import sl.nim.netease.com.gitlijar.db.DaoSession;

public class BaseApplication extends Application {

    private static DaoSession daoSeesion;

    @Override
    public void onCreate() {
        super.onCreate();

        initDatabase();
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"SqBean.db");
        //获取可写数据库
        SQLiteDatabase db=helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster=new DaoMaster(db);
        //获取Dao对象管理者
        daoSeesion=daoMaster.newSession();
    }

    public static DaoSession getDaoInstant(){
        return daoSeesion;
    }

}
