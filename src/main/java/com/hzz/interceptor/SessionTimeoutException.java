package com.hzz.interceptor;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "session timeout.")
public class SessionTimeoutException extends RuntimeException {
    public SessionTimeoutException() {
        super();
    }

}
