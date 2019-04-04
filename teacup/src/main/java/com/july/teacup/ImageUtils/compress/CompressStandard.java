package com.july.teacup.ImageUtils.compress;

public enum CompressStandard {
    HORIZON(0),
    VERTICAL(1);

    int mSideData;

    CompressStandard(int standardSide){
        mSideData=standardSide;
    }

    public int getValues(){
        return mSideData;
    }
}
