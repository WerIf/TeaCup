package com.july.teacup.basics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.july.teacup.annotation.autoknife.FindKnifeProcess;
import com.july.teacup.annotation.autowired.AutoWriedProcess;



public abstract class BaseFragment extends Fragment {

    public static final String TAG=BaseFragment.class.getSimpleName();
    private View mRootView;

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



}
