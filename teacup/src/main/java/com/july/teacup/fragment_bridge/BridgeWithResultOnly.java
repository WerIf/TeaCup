package com.july.teacup.fragment_bridge;

public abstract class BridgeWithResultOnly<Result> extends BaseBridge {
    public BridgeWithResultOnly(String name) {
        super(name);
    }

    public abstract Result bridge();
}
