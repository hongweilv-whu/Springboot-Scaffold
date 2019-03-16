package com.whu.sres.lhw.exception;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Desc: 系统级，非受检异常类
 */
public final class ScaffoldSystemException extends RuntimeException {

    // 异常编码
    private transient ExceptionCode exceptionCode;

    // 异常的上下文环境数据
    private final Map<String, String> contextData = Maps.newLinkedHashMap();

    public static ScaffoldSystemException wrap(Throwable cause) {
        return wrap(cause, null);
    }

    public static ScaffoldSystemException wrap(Throwable cause, ExceptionCode exceptionCode) {
        if (cause instanceof ScaffoldSystemException) {
            // 没有嵌套多层的必要
            ScaffoldSystemException e = (ScaffoldSystemException) cause;
            if (null != exceptionCode && !exceptionCode.equals(e.getExceptionCode())) {
                return new ScaffoldSystemException(e.getMessage(), e, exceptionCode);
            }
            return e;
        } else {
            // 嵌套一层异常
            return new ScaffoldSystemException(cause.getMessage(), cause, exceptionCode);
        }
    }

    private ScaffoldSystemException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public ScaffoldSystemException(ExceptionCode exceptionCode) {
        super(exceptionCode.toString());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public Map<String, String> getContextData() {
        return contextData;
    }

    public ScaffoldSystemException set(String name, String value) {
        this.contextData.put(name, value);
        return this;
    }

    @Override
    public String getMessage() {
        if (null == exceptionCode) {
            return super.getMessage();
        }

        StringBuilder msg = new StringBuilder();
        msg.append(exceptionCode.getMsg());
        if (0 != contextData.size()) {
            msg.append("\n").append(JSON.toJSONString(contextData));
        }
        return msg.toString();
    }

    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    @Override
    public void printStackTrace(PrintWriter s1) {
        WrappedPrintWriter wpw = new WrappedPrintWriter(s1);
        synchronized (wpw.lock()) {
            wpw.println(this);
            wpw.println("\t-------------------------------");
            if (exceptionCode != null) {
                wpw.println("\t" + exceptionCode + ":" + exceptionCode.getMsg() + ":" + exceptionCode.getClass().getName());
            }
            for (Entry<String, String> entry : contextData.entrySet()) {
                wpw.println("\t" + entry.getKey() + "=[" + entry.getValue() + "]");
            }
            wpw.println("\t-------------------------------");
            StackTraceElement[] trace = getStackTrace();

            for (StackTraceElement traceElement : trace) {
                wpw.println("\tat " + traceElement);
            }

            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(wpw.printWriter);
            }
            wpw.printWriter.flush();
        }
    }

    private static class WrappedPrintWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        Object lock() {
            return printWriter;
        }

        void println(Object o) {
            printWriter.println(o);
        }
    }
}
