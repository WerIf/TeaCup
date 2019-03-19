package com.lily.gitlijar.slide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lily.gitlijar.bean.BasicsBean;
import com.lily.gitlijar.bean.BasicsComparableBean;
import com.lily.gitlijar.recycler.GitliViewHolder;
import com.lily.gitlijar.recycler.OnBackViewHolder;

import java.util.List;

public class SlideIndexAdapter<T extends BasicsComparableBean> extends RecyclerView.Adapter<GitliViewHolder> {
    private final Context mContext;
    private final int mIndexViewId;
    private List<T> mItemSize;
    private int mResourceId;

    private OnBackViewHolder onBackViewHolder;

    public void setOnBackViewHolder(OnBackViewHolder onBackViewHolder) {
        this.onBackViewHolder = onBackViewHolder;
    }

    public SlideIndexAdapter(Context context, int resourceId,int indexViewId, List<T> itemSize){
        mContext=context;
        mResourceId=resourceId;
        mItemSize=itemSize;
        mIndexViewId=indexViewId;
    }

    @NonNull
    @Override
    public GitliViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /**
         * 避免在layoutItem显示是尺寸不能正常显示
         */
        View mView=LayoutInflater.from(mContext).inflate(mResourceId,null);
        return new GitliViewHolder(mView,mIndexViewId);
    }

    @Override
    public void onBindViewHolder(@NonNull GitliViewHolder gitliViewHolder, int position) {

        if(gitliViewHolder.mIndexView==null){
            throw new NullPointerException("未找到头部 IndexView");
        }
        String currentWord=mItemSize.get(position).getComparableStr().charAt(0)+"";
        if(position>0){
            String lastWord=mItemSize.get(position-1).getComparableStr().charAt(0)+"";

            if(lastWord.equals(currentWord)){
                gitliViewHolder.mIndexView.setVisibility(View.GONE);
            }else{
                //当首字母不一样是需要显示首字母
                //由于布局时复用的，所以在需要显示的时候，再次将one设置为可见
                gitliViewHolder.mIndexView.setVisibility(View.VISIBLE);
                gitliViewHolder.mIndexView.setText(currentWord);

            }
        }else{
            //当从下往上滑 复用下面的one
            gitliViewHolder.mIndexView.setVisibility(View.VISIBLE);
            gitliViewHolder.mIndexView.setText(currentWord);
        }

        onBackViewHolder.backViewHolder(gitliViewHolder,position);
    }

    @Override
    public int getItemCount() {
        return mItemSize.size();
    }
}
