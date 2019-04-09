package com.july.teacup.recycler;

import android.view.View;

public interface OnBackViewHolder {

    void backViewHolder(GitliViewHolder mViewHolder, int position);

    void clickViewHolder(View currentView,int currentPosition);
}
