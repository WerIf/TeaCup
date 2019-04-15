package com.july.teacup.fragment_bridge;

public abstract class BridgeWithParamOnly<Param>  extends BaseBridge{
    public BridgeWithParamOnly(String name) {
        super(name);
    }

    public abstract void bridge(Param param);
}
