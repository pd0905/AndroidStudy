package com.example.pdd.mymvcapplication;

public interface ICangTouShi {
    void doRequest(String num, String type, String yayuntype, String key,Bean<CangTouShiBean> back);


    public interface Bean <T> {

        void onError(String msg);
        void onSuccess(T t);

    }

}
