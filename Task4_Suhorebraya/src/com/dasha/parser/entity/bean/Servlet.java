package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Даша on 23.01.2017.
 */
public class Servlet extends XMLElement implements Serializable{
    private static final long serialVersionUID=1L;
    private String servletName;
    private String servletClass;
    private List<InitParam> initParams;

    public Servlet() {
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public List<InitParam> getInitParams() {
        return initParams;
    }

    public void setInitParams(List<InitParam> initParams) {
        this.initParams = initParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Servlet servlet = (Servlet) o;

        if (null==servletName){
            return servletName==servlet.servletName;
        }
        else {
            if (!servletName.equals(servlet.servletName)){
                return false;
            }
        }
        if (null==servletClass){
            return servletClass==servlet.servletClass;
        }
        else {
            if (!servletClass.equals(servlet.servletClass)){
                return false;
            }
        }
        return initParams != null ? initParams.equals(servlet.initParams) : servlet.initParams == null;

    }

    @Override
    public int hashCode() {
        int result = servletName != null ? servletName.hashCode() : 0;
        result = 31 * result + (servletClass != null ? servletClass.hashCode() : 0);
        result = 31 * result + (initParams != null ? initParams.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("servlet: ");
        System.out.println("  servlet-name: "+servletName);
        System.out.println("  servlet-class: "+servletClass);
        if (initParams!=null){
            initParams.forEach(InitParam::printElement);
        }
        System.out.println("------------");
    }
}
