package com.july.teacup.interfs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class TeaCupItemTouchHelper extends ItemTouchHelper.Callback {

    private final ItemTouchHelperCallBack mHelpCallBack;

    public TeaCupItemTouchHelper(ItemTouchHelperCallBack helperCallBack){
        this.mHelpCallBack=helperCallBack;
    }

    /**
     *  判断滑动动作
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int left = ItemTouchHelper.LEFT;
        int up = ItemTouchHelper.UP;
        int right = ItemTouchHelper.RIGHT;
        int down = ItemTouchHelper.DOWN;

        int drag=up | down;
        int swipe=left | right;

        return makeMovementFlags(drag,swipe);
    }


    /**
     *  当ViewHolder上下移动调用
     * @param recyclerView
     * @param viewHolder    选中的ViewHolder
     * @param viewHolder1   滑动时经过的ViewHolder
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {

        mHelpCallBack.onItemTouchMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());

        return false;
    }

    /**
     *  当ViewHolder左右移动调用
     * @param viewHolder    选中的ViewHolder
     * @param i
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        mHelpCallBack.onItemTouchSwipe(viewHolder.getAdapterPosition());
    }

    /**
     *  开启长按ViewHolder拽动功能
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     *  当选中ViewHolder时，可改变当前ViewHolder背景颜色
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     *  释放选中状态
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }
}
