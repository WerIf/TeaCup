package com.teacup.fragment.presenter;

import android.provider.ContactsContract;

import com.teacup.fragment.contract.DataContract;
import com.teacup.fragment.model.DataModel;

import java.util.Map;

public class DataPresenter implements DataContract.Presenter {

    private final DataContract.View mView;

    private DataContract.Model model;

    public DataPresenter(DataContract.View view){
        this.mView=view;

        model=new DataModel(this);
    }


    public void start(String url,Map<String,String> dataMap){
        model.request(url,dataMap);
    }

    @Override
    public void success(String success) {
        mView.success();
    }

    @Override
    public void failed(String failed) {
        mView.failed();
    }
}
