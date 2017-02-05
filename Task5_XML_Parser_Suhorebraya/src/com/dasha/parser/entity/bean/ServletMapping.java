package com.dasha.parser.entity.bean;

import com.dasha.parser.entity.element.XMLElement;

import java.io.Serializable;

/**
 * Created by Даша on 23.01.2017.
 */
public class ServletMapping extends XMLElement implements Serializable {
    private static final long serialVersionUID=1L;
    private String servletName;
    private String urlPattern;

    public ServletMapping() {
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        ServletMapping servletMapping = (ServletMapping) o;
        if (null==servletName){
            return servletName==servletMapping.servletName;
        }
        else {
            if (!servletName.equals(servletMapping.servletName)){
                return false;
            }
        }
        if (null==urlPattern){
            return urlPattern==servletMapping.urlPattern;
        }
        else {
            if (!urlPattern.equals(servletMapping.urlPattern)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = servletName != null ? servletName.hashCode() : 0;
        result = 31 * result + (urlPattern != null ? urlPattern.hashCode() : 0);
        return result;
    }

    @Override
    public void printElement() {
        System.out.println("servlet-mapping: ");
        System.out.println("  servlet-name: "+servletName);
        System.out.println("  url-pattern: "+urlPattern);
        System.out.println("------------");
    }
}
