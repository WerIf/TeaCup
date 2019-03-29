package com.july.teacup.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class AppUtils {

    /**
     * 如果版本号发生改变，那么AndFix会把缓存好的拆分包清除掉
     */

    /**
     *  获取应用程序的versionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        String versionName="1.0.0";
        try {
            PackageManager packageManager=context.getPackageManager();
            PackageInfo info=packageManager.getPackageArchiveInfo(context.getPackageName(),0);
            versionName=info.versionName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * @param v1  服务器版本
     * @param v2  当前版本
     * @return
     */
    public static boolean compareVersions(String v1, String v2) {
        //判断是否为空数据
        if (TextUtils.equals(v1, "") || TextUtils.equals(v2, "")) {
            return false;
        }
        String[] str1 = v1.split("\\.");
        String[] str2 = v2.split("\\.");

        if (str1.length == str2.length) {
            for (int i = 0; i < str1.length; i++) {
                if (Integer.parseInt(str1[i]) > Integer.parseInt(str2[i])) {
                    return true;
                } else if (Integer.parseInt(str1[i]) < Integer.parseInt(str2[i])) {
                    return false;
                } else if (Integer.parseInt(str1[i]) == Integer.parseInt(str2[i])) {

                }
            }
        } else {
            if (str1.length > str2.length) {
                for (int i = 0; i < str2.length; i++) {
                    if (Integer.parseInt(str1[i]) > Integer.parseInt(str2[i])) {
                        return true;
                    } else if (Integer.parseInt(str1[i]) < Integer.parseInt(str2[i])) {
                        return false;

                    } else if (Integer.parseInt(str1[i]) == Integer.parseInt(str2[i])) {
                        if (str2.length == 1) {
                            continue;
                        }
                        if (i == str2.length - 1) {

                            for (int j = i; j < str1.length; j++) {
                                if (Integer.parseInt(str1[j]) != 0) {
                                    return true;
                                }
                                if (j == str1.length - 1) {
                                    return false;
                                }

                            }
                            return true;
                        }
                    }
                }
            } else {
                for (int i = 0; i < str1.length; i++) {
                    if (Integer.parseInt(str1[i]) > Integer.parseInt(str2[i])) {
                        return true;
                    } else if (Integer.parseInt(str1[i]) < Integer.parseInt(str2[i])) {
                        return false;

                    } else if (Integer.parseInt(str1[i]) == Integer.parseInt(str2[i])) {
                        if (str1.length == 1) {
                            continue;
                        }
                        if (i == str1.length - 1) {
                            return false;

                        }
                    }

                }
            }
        }
        return false;
    }
}
