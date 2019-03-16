package com.whu.sres.lhw.bean;

import lombok.Data;

import java.io.Serializable;


/**
 * 返回对象包装类(带泛型)
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int FAIL = -1;

    public static final int SUCCESS = 0;

    private int code = SUCCESS;

    private String error;

    /**
     * 返回的数据
     */
    private T result;

    private ResultBean() {
    }

    public static <T> ResultBean success(T result) {
        ResultBean b = new ResultBean();
        b.code = SUCCESS;
        b.result = result;
        return b;
    }

    public static ResultBean fail(String errorMsg) {
        ResultBean b = new ResultBean();
        b.setCode(FAIL);
        b.setError(errorMsg);
        return b;
    }

    public static ResultBean fail(Throwable exception) {
        ResultBean b = new ResultBean();
        b.code = FAIL;
        b.error = exception.getMessage();
        return b;
    }
}
