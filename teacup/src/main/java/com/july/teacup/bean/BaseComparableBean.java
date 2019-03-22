package com.july.teacup.bean;

import com.july.teacup.pinyin.PinYinUtil;

public class BaseComparableBean implements Comparable<BaseComparableBean> {


    private String comparableStr;

    public BaseComparableBean(String mTransition){
        setComparableStr(PinYinUtil.getPinYin(mTransition));
    }

    public void setComparableStr(String comparableStr) {
        this.comparableStr = comparableStr;
    }

    public String getComparableStr() {
        return comparableStr;
    }

    @Override
    public int compareTo(BaseComparableBean basicsBean) {
        return this.getComparableStr().compareTo(basicsBean.getComparableStr());
    }
}
