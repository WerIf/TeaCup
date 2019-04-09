package com.teacup.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.july.teacup.annotation.autoknife.FindView;
import com.july.teacup.basics.BaseActivity;
import com.july.teacup.recycler.GitliRecyclerAdapter;
import com.july.teacup.recycler.GitliViewHolder;
import com.july.teacup.recycler.OnBackViewHolder;

import com.teacup.BaseApplication;

import com.teacup.R;
import com.teacup.db.DaoUtils;
import com.teacup.db.SqBean;

public class SqliteActivity extends BaseActivity {

    Toolbar toolbar;

    String[] strings = new String[]{
            "http://s7.sinaimg.cn/mw690/001m1Utdzy6ZLnVyRxQe6&690",
            "http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg",
            "http://img1.3lian.com/img013/v5/21/d/84.jpg",
            "http://img15.3lian.com/2015/f2/57/d/93.jpg",
            "http://img.pptjia.com/image/20180117/cf641c8d7510aa87154ffed150481e42.jpg",
            "http://img18.3lian.com/d/file/201709/21/a05161a4469dc5ef8be88ee217d53d92.jpg",
            "http://img1.ali213.net/patchpic/2017/02/07/2017020750046494.jpg",
            "http://pic36.nipic.com/20131213/6704106_223232205107_2.png",
            "https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0D/0A/ChMkJ1cODyOICj1uABSKWVmi4vsAAP1HALS3dMAFIpx321.jpg",
            "http://www.deskier.com/uploads/allimg/170807/1-1FPG41410.jpg"
    };

    @FindView(R.id.image_list)
    RecyclerView recyclerView;

    private DaoUtils daoUtils;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SqliteActivity.class));
    }

    @Override
    public void beforeOnCreate() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_sqlite;
    }


    @Override
    public void init(Bundle savedInstanceState) {

        daoUtils = new DaoUtils((BaseApplication) getApplication());

        for (int i = 0; i < 10; i++) {
            SqBean sqBean = new SqBean();
            sqBean.setName("gitli_" + i);
            sqBean.setUid((long) i);
            sqBean.setPath(strings[i]);

            daoUtils.insertObject(sqBean);
        }

        initData();
    }

    public void initData() {
        GitliRecyclerAdapter recyclerAdapter = new GitliRecyclerAdapter(this, R.layout.layout_image_item, 10);
        recyclerAdapter.setOnBackViewHolder(new OnBackViewHolder() {
            @Override
            public void backViewHolder(GitliViewHolder mViewHolder, int position) {

                SqBean bean = daoUtils.queryObjectFormId(position);
                Log.e("TAG", "println result position is:" + bean.getPath());

                mViewHolder.setImageToView(SqliteActivity.this, R.id.item_img,bean.getPath());

            }

            @Override
            public void clickViewHolder(View currentView, int currentPosition) {

            }
        });

        recyclerView.setAdapter(recyclerAdapter);

        GridLayoutManager manager = new GridLayoutManager(this, 3);

        recyclerView.setLayoutManager(manager);
    }


}
