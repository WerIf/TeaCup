package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
//import com.july.teacup.view.BazierView;
import com.teacup.R;

public class BazierActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnTouchListener {


    @FindView(R.id.one)
    RadioButton one;
    @FindView(R.id.two)
    RadioButton two;
    @FindView(R.id.group)
    RadioGroup group;
    @FindView(R.id.test)
    Button bazier;

    public static void start(Context context){
        context.startActivity(new Intent(context,BazierActivity.class));
    }



    @Override
    public void beforeOnCreate() {

    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_bazier;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        group.setOnCheckedChangeListener(this);

        bazier.setOnTouchListener(this);
    }

    @OnClick(R.id.test)
    public void onClick(View view){
        Log.d("TAG", "onClick execute");
    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case R.id.one:
//                bazier.chooseOther(true);
                break;
            case R.id.two:
//                bazier.chooseOther(false);
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("TAG", "onTouch execute, action " + event.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG","println log is:"+event.getAction());
        return false;
    }
}
