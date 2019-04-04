package com.teacup.db;

import com.july.teacup.application.TeaCupBaseApplication;
import com.teacup.BaseApplication;

import java.util.List;

public class DaoUtils {

    private final DaoSession daoSession;

    public DaoUtils(BaseApplication application) {
        daoSession =application.getDaoInstant();
    }

    /**
     * 添加数据 如果有重复的则覆盖
     *
     * @param sqBean
     */
    public void insertObject(SqBean sqBean) {
        daoSession.getSqBeanDao().insertOrReplace(sqBean);
    }
    


    /**
     *      通过键删除
     * @param key
     */
    public void deleteObjectFromId(long key) {
        daoSession.getSqBeanDao().deleteByKey(key);

    }

    /**
     *  通过对象删除
     * @param sqBean
     */
    public void deleteObjectFormOb(SqBean sqBean) {
        daoSession.getSqBeanDao().delete(sqBean);
    }

    /**
     *      查询所有
     * @return
     */
    public List<SqBean> queryObjectAll(){
        return daoSession.getSqBeanDao().loadAll();
    }

    /**
     *       根据key查询所有
     * @param key
     * @return
     */
    public SqBean queryObjectFormId(long key){
        return daoSession.getSqBeanDao().load(key);
    }

    public List<SqBean> queryObjectFormCondition(String where, String selectionArg){
        return daoSession.getSqBeanDao().queryRaw(where,selectionArg);
    }
}
