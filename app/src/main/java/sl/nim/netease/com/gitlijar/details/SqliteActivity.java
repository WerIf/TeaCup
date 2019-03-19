package sl.nim.netease.com.gitlijar.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lily.gitlijar.basics.BaseActivity;
import com.lily.gitlijar.basics.ToolBarOptions;

import sl.nim.netease.com.gitlijar.R;
import sl.nim.netease.com.gitlijar.db.SqBean;

public class SqliteActivity extends BaseActivity {

    Toolbar toolbar;

    String[] strings=new String[]{
            "http://s7.sinaimg.cn/mw690/001m1Utdzy6ZLnVyRxQe6&690",
            ""
    };

    public static void start(Context context) {
        context.startActivity(new Intent(context,SqliteActivity.class));
    }

    @Override
    public void beforeOnCreate() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_sqlite;
    }



    @Override
    public void init(Bundle savedInstanceState) {

        for (int i=0;i<20;i++){
            SqBean sqBean=new SqBean();
            sqBean.setName("gitli_"+i);
            sqBean.setUid("gl_"+i);
//            sqBean.setPath();
        }
    }

    protected void setToolbar(int toolbarId,int titleId,int logoId){
        toolbar=findViewById(toolbarId);
        toolbar.setLogo(logoId);
        toolbar.setTitle(titleId);
        setSupportActionBar(toolbar);
    }

    protected void setToolbar(int toolBarId,ToolBarOptions options){

    }



}
