package com.rock.okhttp3_demo.https.bean;

/**
 * Created by Rock on 2018/4/28.
 */

public class StringNameValueBean extends NameValueBean<String,String> {
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getValue() {
        return super.getValue();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    public StringNameValueBean() {
    }

    public StringNameValueBean(String name, String value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return " ["+getName()+" = "+getValue()+"] ";
    }
}
