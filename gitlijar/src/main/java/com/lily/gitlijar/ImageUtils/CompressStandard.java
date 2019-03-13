package com.lily.gitlijar.ImageUtils;

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
