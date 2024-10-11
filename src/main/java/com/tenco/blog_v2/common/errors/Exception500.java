package com.tenco.blog_v2.common.errors;

public class Exception500 extends RuntimeException{

    // throw new Exception500("야 너 잘못 던졌어");
    public Exception500(String msg) {
        super(msg);
    }



}
