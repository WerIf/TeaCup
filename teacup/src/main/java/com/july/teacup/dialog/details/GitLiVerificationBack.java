package com.july.teacup.dialog.details;

/**
 *  CountDownTimer 回调
 */
public interface GitLiVerificationBack {

    /**
     * 每一次变化 回传时间
     * @param millisUntilFinished
     */
    void onTick(long millisUntilFinished);

    /**
     * 完成回调
     */
    void onFinish();
}
