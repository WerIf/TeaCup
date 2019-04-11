package com.july.teacup.interfs;

public interface ItemTouchHelperCallBack {

    /**
     *  拽动viewHolder时，改变当前viewHolder和目标viewHolder的位置
     * @param srcPosition
     * @param targetPosition
     */
    void onItemTouchMove(int srcPosition,int targetPosition);

    /**
     *  侧滑viewHolder时，删除当前viewHolder位置
     * @param movePosition
     */
    void onItemTouchSwipe(int movePosition);

}
