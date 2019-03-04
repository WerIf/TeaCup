package sl.nim.netease.com.gitlijar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lily.gitlijar.annotation.autoknife.FindKnifeProcess;
import com.lily.gitlijar.annotation.autoknife.OnClick;
import com.lily.gitlijar.annotation.autowired.AutoWriedProcess;
import com.lily.gitlijar.toast.ToastUtils;

import sl.nim.netease.com.gitlijar.details.CardViewActivity;
import sl.nim.netease.com.gitlijar.details.CoordLayoutActivity;
import sl.nim.netease.com.gitlijar.details.SheetActivity;
import sl.nim.netease.com.gitlijar.details.ToolBarActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);

//        btn.setText("click this");

//        CoordLayoutActivity.start(this);
    }

    @OnClick({R.id.toolBar,R.id.cardView,R.id.coordinator,R.id.sheet})
    public void onClick(View view){
       switch (view.getId()){
           case R.id.toolBar:
//               ToastUtils.makeText(this,"toolbar",ToastUtils.LENGTH_SHORT).show();
               ToolBarActivity.start(this);
               break;
           case R.id.cardView:
               CardViewActivity.start(this);
               break;
           case R.id.coordinator:
               CoordLayoutActivity.start(this);
               break;
           case R.id.sheet:
               SheetActivity.start(this);
               break;
//               default:
//                   ToastUtils.makeText(this,"hello world",ToastUtils.LENGTH_SHORT).show();
//               break;
       }
    }
}
