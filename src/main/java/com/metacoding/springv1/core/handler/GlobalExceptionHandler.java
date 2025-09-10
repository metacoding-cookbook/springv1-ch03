package com.metacoding.springv1.core.handler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.metacoding.springv1.core.util.Script;
import com.metacoding.springv1.core.handler.ex.Exception401;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) 
    @ResponseBody
    public String myHandler(Exception e){
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(Exception401.class)
    @ResponseBody
    public String myHandler401(Exception401 e) {
        return Script.href("/login-form", e.getMessage());
    }
    
}

