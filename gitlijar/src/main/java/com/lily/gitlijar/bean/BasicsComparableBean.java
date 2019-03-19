package com.lily.gitlijar.bean;

import com.lily.gitlijar.pinyin.PinYinUtil;

public class BasicsComparableBean implements Comparable<BasicsComparableBean> {


    private String comparableStr;

    public BasicsComparableBean(String mTransition){
        setComparableStr(PinYinUtil.getPinYin(mTransition));
    }

    public void setComparableStr(String comparableStr) {
        this.comparableStr = comparableStr;
    }

    public String getComparableStr() {
        return comparableStr;
    }

    @Override
    public int compareTo(BasicsComparableBean basicsBean) {
        return this.getComparableStr().compareTo(basicsBean.getComparableStr());
    }
}
