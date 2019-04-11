package com.july.teacup.interfs;

import android.support.v7.widget.RecyclerView;

public interface ItemTouchHelperStartDrag {
    /**
     *  回调通知开始拽动
     * @param viewHolder
     */
    void onBeganItemDrag(RecyclerView.ViewHolder viewHolder);
}
