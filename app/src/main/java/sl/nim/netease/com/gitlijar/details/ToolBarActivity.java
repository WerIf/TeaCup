package sl.nim.netease.com.gitlijar.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.lily.gitlijar.annotation.autoknife.FindView;
import com.lily.gitlijar.basics.BaseActivity;

import sl.nim.netease.com.gitlijar.R;

public class ToolBarActivity extends BaseActivity implements View.OnClickListener {

    @FindView(R.id.mToolBar)
    Toolbar toolbar;

    public static void start(Context context){
        context.startActivity(new Intent(context,ToolBarActivity.class));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tool_bar;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        toolbar.setLogo(R.mipmap.icon);
        toolbar.setTitle(R.string.title);
        toolbar.setSubtitle(R.string.subtitle);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);

        toolbar.setNavigationOnClickListener(this);
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
