package com.xiex.quetionbank_android;

/**
 * Created by baby on 2016/12/25.
 * <p>
 * 宜步出行，天天速达
 */

public class BaseRespon {
    private int code;
    private String msg;


    public BaseRespon() {
    }

    public BaseRespon(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseRespon(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
