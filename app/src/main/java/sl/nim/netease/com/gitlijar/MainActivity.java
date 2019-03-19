package sl.nim.netease.com.gitlijar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lily.gitlijar.ImageUtils.load.ImageLoadUtils;
import com.lily.gitlijar.annotation.autoknife.FindKnifeProcess;
import com.lily.gitlijar.annotation.autoknife.FindView;
import com.lily.gitlijar.annotation.autoknife.OnClick;
import com.lily.gitlijar.annotation.autowired.AutoWriedProcess;
import com.lily.gitlijar.dialog.GitLiDialog;
import com.lily.gitlijar.dialog.details.HintDialog;
import com.lily.gitlijar.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import sl.nim.netease.com.gitlijar.been.MessageEvent;
import sl.nim.netease.com.gitlijar.details.CardViewActivity;
import sl.nim.netease.com.gitlijar.details.CoordLayoutActivity;
import sl.nim.netease.com.gitlijar.details.SheetActivity;
import sl.nim.netease.com.gitlijar.details.SqliteActivity;
import sl.nim.netease.com.gitlijar.details.ToolBarActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String string = (String) msg.obj;
            ToastUtils.makeText(MainActivity.this, string, ToastUtils.LENGTH_SHORT).show();

            Log.v("MainActivity", "get println result is:" + string);

        }
    };

    @FindView(R.id.loading_one)
    ImageView loading_one;

    @FindView(R.id.loading_two)
    ImageView loading_two;

    @FindView(R.id.jdk_lambda)
    Button lambda;

    @FindView(R.id.send_evenBus)
    Button evenBus;

    @FindView(R.id.show_evenBus)
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FindKnifeProcess.bind(this);
        AutoWriedProcess.bind(this);

//        btn.setText("click this");

//        CoordLayoutActivity.start(this);

//        Glide.with(this)
//                .load(R.drawable.gif_loading_1)
//                .into(loading_one);
//        Glide.with(this)
//                .load(R.drawable.gif_loading_2)
//                .into(loading_two);

        EventBus.getDefault().register(this);

        ImageLoadUtils.getInstance().ImageTaskDownLoad(loading_one);


        lambda.setOnClickListener(
                view ->
                        GitLiDialog.getService(HintDialog.class, this)
                                .setTitle("HINT TITLE")
                                .setData("hello world")
                                .setOnClickAffirm("确认", view1 -> ToastUtils.makeText(MainActivity.this, "click sure", ToastUtils.LENGTH_LONG).show())
                                .setOnClickDisqualify("取消", view1 -> ToastUtils.makeText(MainActivity.this, "click cancel", ToastUtils.LENGTH_LONG).show())
                                .show()
        );


        initView();
    }

    private void initView() {
        evenBus.setOnClickListener(view -> EventBus.getDefault().post(new MessageEvent("gitly", "15")));
    }

    @OnClick({R.id.toolBar, R.id.cardView, R.id.coordinator, R.id.sheet, R.id.network_urlconnection, R.id.network_httpurlconnection,R.id.show_evenBus})
    public void onClick(View view) {

        Map<String, String> params = initMap();

        switch (view.getId()) {
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
            case R.id.network_urlconnection:

                Log.v("MainActivity", "get println result is:" + params.get("telephone") + "  password:" + params.get("password"));

//              new Thread(new Runnable() {
//                  @Override
//                  public void run() {
//                      String result=HttpRequest.JPostHttpUrlConnection(params,BuildConfig.SERVICE_URL);
//                      Message message=new Message();
//                      message.obj=result;
//                      handler.sendMessage(message);
//                  }
//              }).start();
                break;
            case R.id.network_httpurlconnection:

                Log.v("MainActivity", "get println result is:" + params.get("telephone") + "  password:" + params.get("password"));

//               new Thread(new Runnable() {
//                   @Override
//                   public void run() {
//                       String result=HttpRequest.JPostHttpUrlConnection(params,BuildConfig.SERVICE_URL);
//                       Message message=new Message();
//                       message.obj=result;
//                       handler.sendMessage(message);
//                   }
//               }).start();
                break;
            case R.id.greeDao:
                SqliteActivity.start(this);
                break;
//               default:
//                   ToastUtils.makeText(this,"hello world",ToastUtils.LENGTH_SHORT).show();
//               break;
        }
    }

    private Map<String, String> initMap() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("telephone", "17743266993");
        paramsMap.put("password", "123456");
        return paramsMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent messageEvent) {
        ToastUtils.makeText(this, messageEvent.toString(), ToastUtils.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
