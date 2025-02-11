package com.meli.exceptions;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class QuasarException extends RuntimeException {
    public QuasarException(String message){super(message);}

    public QuasarException(String message, Throwable cause){super(message,cause);}

    public QuasarException(String message, Throwable cause, String parameter){
        super(String.format(message,parameter),cause);
    }

    public QuasarException(String message, String parameter){
        super(String.format(message,parameter));
    }
}
