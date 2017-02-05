package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class ErrorPage extends XMLElement implements Serializable {
    private static final long serialVersionUID=1L;
    private String exceptionType;
    private String location;
    private int errorCode;

    public ErrorPage() {
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        ErrorPage errorPage = (ErrorPage) o;

        if (errorCode != errorPage.errorCode) {return false;}
        if (null==exceptionType){
            return exceptionType==errorPage.exceptionType;
        }
        else {
            if (!exceptionType.equals(errorPage.exceptionType)){
                return false;
            }
        }
        if (null==location){
            return location==errorPage.location;
        }
        else {
            if (!location.equals(errorPage.location)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = exceptionType != null ? exceptionType.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + errorCode;
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("error page: ");
        if (exceptionType!=null){
            System.out.println("  exception-type: "+exceptionType);
        } else {
            System.out.println("  error-code: "+errorCode);
        }
        System.out.println("  location: "+location);
        System.out.println("------------");
    }
}
