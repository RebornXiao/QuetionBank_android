package com.xiex.quetionbank_android;

public class BaseReque {
    private String method;
    private Object detail;

    public BaseReque(String method) {
        this.method = method;
    }

    public BaseReque() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "BaseRespon [method=" + method + ", detail=" + detail + "]";
    }


}
