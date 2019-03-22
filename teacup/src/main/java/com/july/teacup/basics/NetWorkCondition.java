package com.july.teacup.basics;

public interface NetWorkCondition {

    /**
     * 成功
     */
    void onSucceed(Object object);

    /**
     * 失败
     */
    void onFailed(Object object);

    /**
     * 异常
     */
    void onError(Object object);
}
