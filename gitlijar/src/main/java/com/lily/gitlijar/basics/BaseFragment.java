package com.lily.gitlijar.basics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    public static final String TAG=BaseFragment.class.getSimpleName();
    private View mRootView;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutResId(),container,false);
        mUnbinder=ButterKnife.bind(this,mRootView);
        init(savedInstanceState);
        return mRootView;
    }

    public abstract int getLayoutResId();

    protected abstract void init(Bundle savedInstanceState);



    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
