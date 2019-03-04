package sl.nim.netease.com.gitlijar.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lily.gitlijar.basics.BaseActivity;

import sl.nim.netease.com.gitlijar.R;

/**
 *  编写资料地址:https://www.jianshu.com/p/e429bb41bdb6
 */
public class CardViewActivity extends BaseActivity {


    public static void start(Context context){
        context.startActivity(new Intent(context,CardViewActivity.class));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_card_view;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public void beforeOnCreate() {

    }
}
