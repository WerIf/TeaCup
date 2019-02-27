package sl.nim.netease.com.gitlijar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lily.gitlijar.annotation.autoknife.FindKnifeProcess;
import com.lily.gitlijar.annotation.autoknife.FindView;
import com.lily.gitlijar.annotation.autoknife.OnClick;
import com.lily.gitlijar.annotation.autowired.AutoWriedProcess;
import com.lily.gitlijar.toast.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @FindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);

        btn.setText("click this");
    }

    @OnClick({R.id.btn})
    public void onClick(View view){
        ToastUtils.makeText(this,"hello world",ToastUtils.LENGTH_SHORT).show();
    }
}
