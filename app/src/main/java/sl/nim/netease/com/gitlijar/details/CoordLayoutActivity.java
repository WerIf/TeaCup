package sl.nim.netease.com.gitlijar.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.july.teacup.adaptive.NavigationBar;
import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;

import sl.nim.netease.com.gitlijar.R;

public class CoordLayoutActivity extends BaseActivity {

    @FindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Context context){
        context.startActivity(new Intent(context,CoordLayoutActivity.class));
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
    }

    @Override
    public void beforeOnCreate() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
