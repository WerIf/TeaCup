package com.lily.gitlijar.been;

import com.lily.gitlijar.pinyin.PinYinUtil;

public class BasicsBean implements Comparable<BasicsBean> {


    private String comparableStr;

    public BasicsBean(String mTransition){
        setComparableStr(PinYinUtil.getPinYin(mTransition));
    }

    public void setComparableStr(String comparableStr) {
        this.comparableStr = comparableStr;
    }

    public String getComparableStr() {
        return comparableStr;
    }

    @Override
    public int compareTo(BasicsBean basicsBean) {
        return this.getComparableStr().compareTo(basicsBean.getComparableStr());
    }
}
