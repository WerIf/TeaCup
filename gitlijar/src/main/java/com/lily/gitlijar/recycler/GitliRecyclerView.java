package com.lily.gitlijar.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GitliRecyclerView extends RecyclerView.Adapter<GitliViewHolder> {

    private final Context mContext;
    private int mItemSize;
    private int mResourceId;

    private OnBackViewHolder onBackViewHolder;

    public void setOnBackViewHolder(OnBackViewHolder onBackViewHolder) {
        this.onBackViewHolder = onBackViewHolder;
    }

    public GitliRecyclerView(Context context, int resourceId, int itemSize){
        mContext=context;
        mResourceId=resourceId;
        mItemSize=itemSize;
    }

    @NonNull
    @Override
    public GitliViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /**
         * 避免在layoutItem显示是尺寸不能正常显示
         */
        View mView=LayoutInflater.from(mContext).inflate(mResourceId,null);
        return new GitliViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull GitliViewHolder gitliViewHolder, int position) {
        onBackViewHolder.backViewHolder(gitliViewHolder,position);
    }

    @Override
    public int getItemCount() {
        return mItemSize;
    }
}
