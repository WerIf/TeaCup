package com.july.teacup.basics;

import com.july.teacup.bean.BaseBean;

public interface FragmentDataListener {
    <T extends BaseBean> void onDataChangeListener(T t);
}
