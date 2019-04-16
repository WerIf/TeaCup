package com.teacup.fragment.model;

import com.teacup.fragment.contract.DataContract;

import java.util.Map;

public class DataModel implements DataContract.Model {

    DataContract.Presenter mPresenter;

    public DataModel(DataContract.Presenter presenter){
        mPresenter=presenter;
    }

    @Override
    public void request(String url, Map<String, String> dataMap) {

        mPresenter.success("");

        mPresenter.failed("");
    }
}
