package com.july.teacup.pinyin;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
    public static String getPinYin(String chinese){
        if(TextUtils.isEmpty(chinese)) return null;

        //由于只能支持单个汉字转换 将汉字转换成字符数组
        char[] charArray=chinese.toCharArray();

        String pinyin="";
        //用于设置转化拼音大小写 或者声调
        HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
        //设置转化拼音为大写字母
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (int i=0;i<charArray.length;i++){
            //过滤空格
            if(Character.isWhitespace(charArray[i])) continue;
            //需要判断是否是汉字
            //汉字占两个字节 一个字节范围是*128~127 所以汉字大于127
            if(charArray[i]>127){
                //可能是汉字
                try {
                    //由于存在多音字
                   String[] pingyinArr= PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);

                    if(pingyinArr!=null){
                        pinyin+=pingyinArr[0];
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    //说明转化失败 不是汉字 如：o(*￣︶￣*)o 那么忽略
                }
            }else{
                //肯定不是汉字
                //可以直接拼接
                pinyin+=charArray[i];
            }
        }
        return pinyin;
    }
}
