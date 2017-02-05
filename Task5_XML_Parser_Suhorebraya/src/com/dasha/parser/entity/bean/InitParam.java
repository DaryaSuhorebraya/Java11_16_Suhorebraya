package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class InitParam extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private String paramName;
    private String paramValue;

    public InitParam() {
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        InitParam initParam=(InitParam) o;
        if (null==paramName){
            return paramName==initParam.paramName;
        }
        else {
            if (!paramName.equals(initParam.paramName)){
                return false;
            }
        }
        if (null==paramValue){
            return paramValue==initParam.paramValue;
        }
        else {
            if (!paramValue.equals(initParam.paramValue)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = paramName != null ? paramName.hashCode() : 0;
        result = 31 * result + (paramValue != null ? paramValue.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("  init-param: ");
        System.out.println("    param-name: "+paramName);
        System.out.println("    param-value: "+paramValue);
    }
}
