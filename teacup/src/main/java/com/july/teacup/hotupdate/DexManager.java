package com.july.teacup.hotupdate;

import android.content.Context;

import com.july.teacup.ImageUtils.TeaCupImage;
import com.july.teacup.ImageUtils.load.ImageLoadUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;


/**
 *
 * setAccessible(boolean bool)
 * 设置应用对象的accessible标志位布尔值。
 *  true指示反射的对象在使用时应该取消Java语言的访问权限检查，即及时是使用private修饰 外部也可以访问
 *  false指示反射的对象在使用时实施Java语言的访问检查
 */
public class DexManager {


    /**
     * @param mContext
     * @param dexFilePath
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    public static void injectDexElements(Context mContext, String dexFilePath) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        ClassLoader pathClassLoader = mContext.getClassLoader();
        File oDexFile = new File(mContext.getDir("odex", mContext.MODE_PRIVATE).getAbsolutePath());
        File patchFile = new File(dexFilePath);
        if (!patchFile.exists()) {
            patchFile.mkdirs();
        }

        Object applicationDexElement = getDexElementByClassLoader(pathClassLoader);

        /*
        * 定义DexClassLoader
        *  第一个参数：dex压缩文件路径
        *  第二个参数：dex解压后存放的目录
        *  第三个参数：C/C++依赖的本地库文件目录，可以为null
        *  第四个参数：上一级的类加载器
        * */
        ClassLoader classLoader=new DexClassLoader(patchFile.getAbsolutePath(),
                oDexFile.getAbsolutePath(),
                null,
                pathClassLoader);

        Object classElement = getDexElementByClassLoader(classLoader);

        applicationDexElement=combineArray(classElement,applicationDexElement);

        injectDexElements(pathClassLoader,applicationDexElement);

    }

    private static void injectDexElements(ClassLoader classLoader,Object dexElement) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class<?> classLoaderClass=Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathListField=classLoaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList=pathListField.get(classLoader);


        Class<?> pathListClass=pathList.getClass();
        Field dexElementsField=pathListClass.getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        dexElementsField.set(pathList,dexElement);

    }


    /**
     *      合并两个dexElements数组
     * @param arrayLhs
     * @param arrayRhs
     * @return
     */
    private static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> localClass = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);
        int j = i + Array.getLength(arrayRhs);
        Object result = Array.newInstance(localClass, j);

        for (int k = 0; k < j; ++k) {
            if(k<i){
                Array.set(result,k,Array.get(arrayLhs,k));
            }else{
                Array.set(result,k,Array.get(arrayRhs,k-i));
            }
        }

        return result;
    }


    /**
     * 获取classLoader中的DexElement
     *
     * @param classLoader
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Object getDexElementByClassLoader(ClassLoader classLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> classLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
        Field pathListField = classLoaderClass.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        Class<?> pathListClass = pathList.getClass();
        Field dexElementsField = pathListClass.getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object dexElements = dexElementsField.get(pathList);

        return dexElements;
    }
}
