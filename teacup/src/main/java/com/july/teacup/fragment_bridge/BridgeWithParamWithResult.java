package com.july.teacup.fragment_bridge;

public abstract class BridgeWithParamWithResult<Param,Result> extends BaseBridge {
    public BridgeWithParamWithResult(String name) {
        super(name);
    }

    public abstract Result bridge(Param param);
}
