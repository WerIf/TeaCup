package com.teacup.fragment.contract;

import java.util.Map;

public interface DataContract {
    interface Model {
        void request(String url, Map<String,String> dataMap);
    }

    interface View {

        void success();

        void failed();
    }

    interface Presenter {

        void success(String success);

        void failed(String failed);
    }
}
