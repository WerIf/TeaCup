package sl.nim.netease.com.gitlijar.db;

import java.util.List;

import sl.nim.netease.com.gitlijar.BaseApplication;

public class DaoUtils {


    /**
     * 添加数据 如果有重复的则覆盖
     *
     * @param sqBean
     */
    public void insertObject(SqBean sqBean) {
        BaseApplication.getDaoInstant().getSqBeanDao().insert(sqBean);
    }


    /**
     *      通过键删除
     * @param key
     */
    public void deleteObjectFromId(String key) {
        BaseApplication.getDaoInstant().getSqBeanDao().deleteByKey(key);

    }

    /**
     *  通过对象删除
     * @param sqBean
     */
    public void deleteObjectFormOb(SqBean sqBean) {
        BaseApplication.getDaoInstant().getSqBeanDao().delete(sqBean);
    }

    /**
     *      查询所有
     * @return
     */
    public List<SqBean> queryObjectAll(){
        return BaseApplication.getDaoInstant().getSqBeanDao().loadAll();
    }

    /**
     *       根据key查询所有
     * @param key
     * @return
     */
    public SqBean queryObjectFormId(String key){
        return BaseApplication.getDaoInstant().getSqBeanDao().load(key);
    }

    public List<SqBean> queryObjectFormCondition(String where,String selectionArg){
        return BaseApplication.getDaoInstant().getSqBeanDao().queryRaw(where,selectionArg);
    }
}
