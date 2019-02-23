package com.rdc.bms.q_comic.base;

public abstract class BaseDTO<E> {

    protected int code;

    protected String msg;

    public abstract E transform();

    public final boolean isSuccess(){
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? "" : msg;
    }
}
