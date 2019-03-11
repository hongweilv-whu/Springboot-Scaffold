package com.whu.sres.lhw.exception;

/**
 * Description: 权限异常
 * Created by lvhw on 2019/3/6 8:50.
 */
public enum PermissionException implements ExceptionCode {
    UN_AUTHORIZED(1, "无权限访问")
    ;

    private int code;

    private String msg;

    PermissionException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
