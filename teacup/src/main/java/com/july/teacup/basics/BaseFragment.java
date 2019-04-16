package com.july.teacup.basics;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.july.teacup.annotation.autoknife.FindKnifeProcess;
import com.july.teacup.annotation.autowired.AutoWriedProcess;
import com.july.teacup.fragment_bridge.BridgeManager;


public abstract class BaseFragment  extends Fragment implements FragmentDataListener {

    public static final String TAG=BaseFragment.class.getSimpleName();
    private View mRootView;

    protected BridgeManager bridgeManager;

    public void setBridgeManager(BridgeManager bridgeManager) {
        this.bridgeManager = bridgeManager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutResId(),container,false);
        FindKnifeProcess.bind(this,mRootView);
        AutoWriedProcess.bind(this);
        init(savedInstanceState);
        return mRootView;
    }

    public abstract int getLayoutResId();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof BaseActivity){
            BaseActivity baseActivity= (BaseActivity) context;

//            baseActivity.setFunctionForFragment(getFragmentTag(),getFragmentType());

            onBridge(baseActivity);
        }
    }

    /**
     *  获取当前Fragment的View对象
     * @return
     */
    protected View getCurrentView(){
        return mRootView;
    }


    /**
     * 具体实现activity和fragment的通讯
     * @param baseActivity
     */
    public void onBridge(BaseActivity baseActivity){ }



}
