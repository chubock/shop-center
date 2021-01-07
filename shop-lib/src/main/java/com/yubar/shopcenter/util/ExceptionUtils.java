package com.yubar.shopcenter.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtils {

    public static ResponseStatusException badRequest() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public static ResponseStatusException unAuthorized() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    public static ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public static ResponseStatusException badRequest(Throwable throwable) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, throwable.getMessage(), throwable);
    }

    public static ResponseStatusException unAuthorized(Throwable throwable) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, throwable.getMessage(), throwable);
    }

    public static ResponseStatusException notFound(Throwable throwable) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, throwable.getMessage(), throwable);
    }
}
