package com.zzxx.exam.ui;

/**
 * 自定义异常只需要添加构造器
 */
public class IdOrPwdException extends Exception {
    public IdOrPwdException() {
        super();
    }

    public IdOrPwdException(String message) {
        super(message);
    }
}
