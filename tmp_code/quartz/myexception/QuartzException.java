package com.example.quartz.myexception;

public class QuartzException extends RuntimeException {

    private String msg;

    private int code = 500;

    public QuartzException(String msg,int code){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public QuartzException(String msg){
        super(msg);
        this.msg = msg;
    }

    public QuartzException(String msg,Throwable e){
        super(msg,e);
        this.msg = msg;
    }

    public QuartzException(String msg,int code,Throwable e){
        super(msg,e);
        this.code = code;
    }

}
