package com.dasha.parser.service.exception;

/**
 * Created by Даша on 24.01.2017.
 */
public class ServiceException extends Exception{
    private static final long serialVersionUID=1L;

    public ServiceException(){
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Exception e){
        super(e);
    }
    public ServiceException(String message, Exception e){
        super(message,e);
    }
}
