package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;


public class SheetActivity extends BaseActivity {

    public static final int STATE_DRAGGING = 1;//手指接触正在移动
    public static final int STATE_SETTLING = 2;//手指释放正在移动
    public static final int STATE_EXPANDED = 3;//展开
    public static final int STATE_COLLAPSED = 4;//收起
    public static final int STATE_HIDDEN = 5;//隐藏

    private boolean isOpen;
    @FindView(R.id.fab)
    FloatingActionButton fab;
    @FindView(R.id.bottom_sheet)
    LinearLayout mBottomSheet;

    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;
    private BottomSheetDialog mBottomSheetDialog;

    public static void start(Context context){
        context.startActivity(new Intent(context,SheetActivity.class));
    }
    @Override
    public int getContentViewResId() {
        return R.layout.activity_sheet;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View view, int i) {
                ToastUtils.makeText(SheetActivity.this,"newState:" + i,ToastUtils.LENGTH_SHORT).show();
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }

    @Override
    public BridgeManager backBaseBridge(BridgeManager bridgeManager) {
        return null;
    }

    @Override
    public void beforeOnCreate() {

    }

    @OnClick(R.id.fab)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
//                if (isOpen) {
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                } else {
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }
//                isOpen = !isOpen;

                mBottomSheetDialog = new BottomSheetDialog(this);
                mBottomSheetDialog.setContentView(R.layout.a_pome_item);
                mBottomSheetDialog.show();
                break;
        }
    }




}
