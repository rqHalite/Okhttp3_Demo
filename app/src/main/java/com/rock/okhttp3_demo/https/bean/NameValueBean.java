package com.rock.okhttp3_demo.https.bean;

/**
 * Created by Rock on 2018/4/28.
 */

public class NameValueBean<N,V> {
    private N name;
    private V value;

    public N getName() {
        return name;
    }

    public void setName(N name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NameValueBean() {
    }

    public NameValueBean(N name, V value) {
        this.name = name;
        this.value = value;
    }
}
