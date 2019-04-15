package com.teacup.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teacup.R;

public class Tab3 extends Fragment {

    public static String INTERFACE = Tab1.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_tab3,null);
        return view;
    }

    public interface T3Interface{
        void onT3FragmentInteraction(String url);
    }
}