package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.toast.ToastUtils;
import com.july.teacup.view.BubbleView;
import com.july.teacup.view.CircleBezierView;
import com.july.teacup.view.DragBubbleView;
import com.teacup.R;

public class TestBezierActivity extends BaseActivity implements BubbleView.OnBubbleStateListener, DragBubbleView.OnBubbleStateListener {

    @FindView(R.id.circle)
    CircleBezierView circle;
    @FindView(R.id.dragBubbleView)
    BubbleView mDragBubbleView;

    public static void start(Context context){
        context.startActivity(new Intent(context,TestBezierActivity.class));
    }

    @Override
    public void beforeOnCreate() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_test_bezier;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mDragBubbleView = findViewById(R.id.dragBubbleView);
        mDragBubbleView.setText("99+");
        mDragBubbleView.setOnBubbleStateListener(this);
    }

    public void start(View view) {
//        circle.startAnimation();

        mDragBubbleView.reCreate();
    }

    @Override
    public void onDrag() {
        ToastUtils.makeText(this,"拖拽气泡",ToastUtils.LENGTH_LONG).show();
    }

    @Override
    public void onMove() {

        ToastUtils.makeText(this,"移动气泡",ToastUtils.LENGTH_LONG).show();
    }

    @Override
    public void onRestore() {

        ToastUtils.makeText(this,"气泡恢复原来位置",ToastUtils.LENGTH_LONG).show();
    }

    @Override
    public void onDismiss() {

        ToastUtils.makeText(this,"气泡消失",ToastUtils.LENGTH_LONG).show();
    }


}
