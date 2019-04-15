package com.teacup.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.july.teacup.annotation.autoknife.OnClick;
import com.july.teacup.basics.BaseFragment;
import com.july.teacup.fragment_bridge.BridgeManager;
import com.july.teacup.toast.ToastUtils;
import com.teacup.R;
import com.teacup.details.EventBusActivity;

public class Tab1 extends Fragment {

    public static String INTERFACE = Tab1.class.getName();

    protected BridgeManager bridgeManager;

    public void setBridgeManager(BridgeManager bridgeManager) {
        this.bridgeManager = bridgeManager;
    }

    TextView textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab1,null);

        textView=view.findViewById(R.id.click);
        textView.setOnClickListener(v -> {
//            ToastUtils.makeText(getContext(),"click button",ToastUtils.LENGTH_LONG).show();

            bridgeManager.invoke(INTERFACE);
        });
        return view;
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EventBusActivity) {
            EventBusActivity busActivity = (EventBusActivity) context;
            busActivity.setFunctionForFragment(getTag());
        }
    }
}
